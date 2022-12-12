package com.va.workercall.ui.main.receiptdetail

import android.os.Bundle
import android.view.View
import com.va.workercall.R
import com.va.workercall.databinding.FragmentReceiptDetailBinding
import com.va.workercall.ui.base.BaseBindingFragment
import com.va.workercall.ui.dialog.DialogBottomCancelReceipt
import com.va.workercall.ui.dialog.DialogConfirmCancel
import com.va.workercall.ui.main.MainViewModel

class ReceiptDetailFragment : BaseBindingFragment<FragmentReceiptDetailBinding, MainViewModel>(), DialogBottomCancelReceipt.CancelReceiptListener, DialogConfirmCancel.OnClickListener {
    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_receipt_detail

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        onClickListener()

        arguments?.let {
            val number = it.getInt("BOOKING_NUMBER")
            binding.tvTitle.text = "Booking No: $number"
            when (number) {
                123123 -> {
                    binding.ivReceipt.setImageResource(R.drawable.img_receipt_waiting)
                }
                234324 -> {
                    binding.ivReceipt.setImageResource(R.drawable.img_receipt_confirmed)
                    binding.btnCancel.visibility = View.VISIBLE
                }
                456565 -> {
                    binding.ivReceipt.setImageResource(R.drawable.img_receipt_cancel)
                }
                789898 -> {
                    binding.ivReceipt.setImageResource(R.drawable.img_receipt_finish)
                    binding.tvByCard.visibility = View.VISIBLE
                    binding.tvByMoney.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun onClickListener() {
        binding.ivBack.setOnClickListener {
            popBackStack()
        }

        binding.btnCancel.setOnClickListener {
            val dialogCancel: DialogBottomCancelReceipt = DialogBottomCancelReceipt(this)
            dialogCancel.show(childFragmentManager, null)
        }

        binding.tvByCard.setOnClickListener {
            navigateScreen(null, R.id.payByCardFragment)
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
        popBackStackWithInclusive(R.id.homeFragment, false)
    }

    override fun onClickMessage() {
        popBackStack()
        val bundle: Bundle = Bundle()
        bundle.putInt("BOOKING_NUMBER", 456565)
        navigateScreen(bundle, R.id.receiptDetailFragment)
    }
}