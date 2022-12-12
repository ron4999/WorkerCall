package com.va.workercall.ui.main.payment

import android.os.Bundle
import android.view.View
import com.va.workercall.R
import com.va.workercall.data.local.SharedPreferenceHelper
import com.va.workercall.databinding.FragmentPaymentBinding
import com.va.workercall.ui.base.BaseBindingFragment
import com.va.workercall.ui.main.MainViewModel

class PaymentFragment : BaseBindingFragment<FragmentPaymentBinding, MainViewModel>() {
    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_payment

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        val isAdd = SharedPreferenceHelper.getBoolean("IS_ADD", false)
        if (!isAdd) {
            binding.cslCard2.visibility = View.GONE
            binding.ivCard2.visibility = View.GONE
        } else {
            binding.cslCard2.visibility = View.VISIBLE
            binding.ivCard2.visibility = View.VISIBLE
        }

        onClickListener()
    }

    private fun onClickListener() {
        binding.ivBack.setOnClickListener {
            SharedPreferenceHelper.storeBoolean("IS_ADD", false)
            popBackStack()
        }

        binding.ivAddPayment.setOnClickListener {
            navigateScreen(null, R.id.addPaymentFragment)
        }
    }

    override fun onPermissionGranted() {

    }

    override fun observerData() {

    }
}