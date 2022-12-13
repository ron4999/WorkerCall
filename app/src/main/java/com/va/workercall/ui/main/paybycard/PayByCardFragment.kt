package com.va.workercall.ui.main.paybycard

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.widget.doOnTextChanged
import com.va.workercall.R
import com.va.workercall.databinding.FragmentPayByCardBinding
import com.va.workercall.ui.base.BaseBindingFragment
import com.va.workercall.ui.dialog.DialogFinishPayment
import com.va.workercall.ui.main.MainViewModel

class PayByCardFragment : BaseBindingFragment<FragmentPayByCardBinding, MainViewModel>(), DialogFinishPayment.OnClickListener {
    private var isChooseCardFirst = false
    private var isChooseCardSecond = false

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_pay_by_card

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        onClickListener()

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            binding.edtPassword.setText("a a a a")
            val dialogFinishPayment : DialogFinishPayment = DialogFinishPayment(this)
            dialogFinishPayment.show(childFragmentManager, null)
        }, 1000)
    }

    private fun onClickListener() {
        binding.cslCard1.setOnClickListener {
            if (!isChooseCardFirst) {
                isChooseCardFirst = true
                binding.ivNextMasterCard.setImageResource(R.drawable.ic_check)

                binding.ivNextVisa.setImageResource(R.drawable.ic_next)
                isChooseCardSecond = false

                binding.tvConfirmChosenNone.visibility = View.INVISIBLE
                binding.tvConfirmChosen.visibility = View.VISIBLE
            } else {
                isChooseCardFirst = false
                binding.ivNextMasterCard.setImageResource(R.drawable.ic_next)

                binding.tvConfirmChosenNone.visibility = View.VISIBLE
                binding.tvConfirmChosen.visibility = View.GONE
            }
        }

        binding.cslCard2.setOnClickListener {
            if (!isChooseCardSecond) {
                isChooseCardSecond = true
                binding.ivNextVisa.setImageResource(R.drawable.ic_check)

                binding.ivNextMasterCard.setImageResource(R.drawable.ic_next)
                isChooseCardFirst = false

                binding.tvConfirmChosenNone.visibility = View.INVISIBLE
                binding.tvConfirmChosen.visibility = View.VISIBLE
            } else {
                isChooseCardSecond = false
                binding.ivNextVisa.setImageResource(R.drawable.ic_next)

                binding.tvConfirmChosenNone.visibility = View.VISIBLE
                binding.tvConfirmChosen.visibility = View.GONE
            }
        }

        binding.tvConfirmChosen.setOnClickListener {
            binding.cslChoosePayment.visibility = View.GONE
            binding.cslInputPassword.visibility = View.VISIBLE
        }
    }

    override fun onPermissionGranted() {

    }

    override fun observerData() {

    }

    override fun onClickGoHome() {
        popBackStackWithInclusive(R.id.homeFragment, false)
    }

    override fun onClickDetail() {
        popBackStackWithInclusive(R.id.homeFragment, false)
        val bundle = Bundle()
        bundle.putBoolean("IS_FROM_PAYMENT_SUCCESS", true)
        bundle.putInt("BOOKING_NUMBER", 789898)
        navigateScreen(bundle, R.id.receiptDetailFragment)
    }
}