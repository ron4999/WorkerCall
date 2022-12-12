package com.va.workercall.ui.main.paybycard

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.core.widget.doOnTextChanged
import com.va.workercall.R
import com.va.workercall.databinding.FragmentPayByCardBinding
import com.va.workercall.ui.base.BaseBindingFragment
import com.va.workercall.ui.main.MainViewModel

class PayByCardFragment : BaseBindingFragment<FragmentPayByCardBinding, MainViewModel>() {
    private var isChooseCardFirst = false
    private var isChooseCardSecond = false

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_pay_by_card

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        onClickListener()

//        binding.edtPassword.doOnTextChanged { text, start, before, count ->
//
//        }

        binding.edtPassword.transformationMethod = PasswordTransformationMethod()

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
}