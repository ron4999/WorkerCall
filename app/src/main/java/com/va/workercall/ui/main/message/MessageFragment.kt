package com.va.workercall.ui.main.message

import android.os.Bundle
import android.view.View
import com.va.workercall.R
import com.va.workercall.databinding.FragmentMessageBinding
import com.va.workercall.ui.base.BaseBindingFragment
import com.va.workercall.ui.main.MainViewModel

class MessageFragment : BaseBindingFragment<FragmentMessageBinding, MainViewModel>() {
    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_message

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        onClickListener()
    }

    private fun onClickListener() {
        binding.ivBack.setOnClickListener {
            popBackStack()
        }

        binding.tvDetail.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("BOOKING_NUMBER", 123123)
            navigateScreen(bundle, R.id.receiptDetailFragment)
        }
    }

    override fun onPermissionGranted() {

    }

    override fun observerData() {

    }
}