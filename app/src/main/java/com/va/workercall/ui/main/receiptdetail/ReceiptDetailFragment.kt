package com.va.workercall.ui.main.receiptdetail

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import com.va.workercall.R
import com.va.workercall.common.Constant
import com.va.workercall.databinding.FragmentReceiptDetailBinding
import com.va.workercall.ui.base.BaseBindingFragment
import com.va.workercall.ui.dialog.DialogBottomCancelReceipt
import com.va.workercall.ui.dialog.DialogConfirmCancel
import com.va.workercall.ui.main.MainActivity.Companion.isWorker
import com.va.workercall.ui.main.MainViewModel

class ReceiptDetailFragment : BaseBindingFragment<FragmentReceiptDetailBinding, MainViewModel>(), DialogBottomCancelReceipt.CancelReceiptListener, DialogConfirmCancel.OnClickListener {
    private var toHome = false
    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_receipt_detail

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            mBackPressedCallback
        )

        onClickListener()

        arguments?.let {
            toHome = it.getBoolean("TO_HOME")
            val number = it.getInt("BOOKING_NUMBER")
            val isFromPaymentSuccess = it.getBoolean("IS_FROM_PAYMENT_SUCCESS")
            binding.tvTitle.text = "Booking No: $number"
            when (number) {
                123123 -> {
                    if (!isWorker) {
                        binding.ivReceipt.setImageResource(R.drawable.img_receipt_waiting)
                    } else {
                        binding.ivReceipt.setImageResource(R.drawable.img_receipt_2_watting)
                        binding.btnCancel.visibility = View.VISIBLE
                    }
                }
                234324 -> {
                    if (!isWorker) {
                        binding.ivReceipt.setImageResource(R.drawable.img_receipt_confirmed)
                    } else {
                        binding.ivReceipt.setImageResource(R.drawable.img_receipt2_confirmed)
                    }
                    binding.btnCancel.visibility = View.VISIBLE
                }
                456565 -> {
                    if (!isWorker) {
                        binding.ivReceipt.setImageResource(R.drawable.img_receipt_cancel)
                    } else {
                        binding.ivReceipt.setImageResource(R.drawable.img_receipt2_canceled)
                    }
                }
                789898 -> {
                    if (!isWorker) {
                        binding.ivReceipt.setImageResource(R.drawable.img_receipt_finish)
                        if (!isFromPaymentSuccess) {
                            binding.tvByCard.visibility = View.VISIBLE
                            binding.tvByMoney.visibility = View.VISIBLE
                            binding.tvSendFeedback.visibility = View.GONE
                        } else {
                            binding.tvByCard.visibility = View.GONE
                            binding.tvByMoney.visibility = View.GONE
                            binding.tvSendFeedback.visibility = View.VISIBLE
                        }
                    } else {
                        binding.ivReceipt.setImageResource(R.drawable.img_receipt2_finished)
                    }


                }
            }
        }
    }

    private val mBackPressedCallback: OnBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                backPress()
            }
        }

    private fun backPress() {
        if (toHome) {
            navigateScreen(null, R.id.homeWorkerFragment)
        } else {
            mBackPressedCallback.isEnabled = false
            requireActivity().onBackPressed()
        }
    }

    private fun onClickListener() {
        binding.ivBack.setOnClickListener {
            backPress()
        }

        binding.btnCancel.setOnClickListener {
            val dialogCancel: DialogBottomCancelReceipt = DialogBottomCancelReceipt(this)
            if (isWorker) {
                dialogCancel.setWorker()
            }
            dialogCancel.show(childFragmentManager, null)
        }

        binding.tvByCard.setOnClickListener {
            navigateScreen(null, R.id.payByCardFragment)
        }

        binding.tvSendFeedback.setOnClickListener {
            popBackStackWithInclusive(R.id.homeFragment, false)
            navigateScreen(null, R.id.rateFragment)
        }
    }

    override fun onPermissionGranted() {

    }

    override fun observerData() {

    }

    override fun onConfirmClick() {
        val dialogConfirmCancel : DialogConfirmCancel = DialogConfirmCancel(this)
        dialogConfirmCancel.show(childFragmentManager, null)
    }

    override fun onClickLater() {
        if (!isWorker) {
            popBackStackWithInclusive(R.id.homeFragment, false)
        } else {
            popBackStackWithInclusive(R.id.homeWorkerFragment, false)
        }
    }

    override fun onClickMessage() {
        popBackStack()
        val bundle: Bundle = Bundle()
        bundle.putInt("BOOKING_NUMBER", 456565)
        navigateScreen(bundle, R.id.receiptDetailFragment)
    }
}