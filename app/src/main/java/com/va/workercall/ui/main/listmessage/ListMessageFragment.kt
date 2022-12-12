package com.va.workercall.ui.main.listmessage

import android.os.Bundle
import android.view.View
import com.va.workercall.R
import com.va.workercall.databinding.FragmentListMessageBinding
import com.va.workercall.ui.base.BaseBindingFragment
import com.va.workercall.ui.main.MainViewModel

class ListMessageFragment : BaseBindingFragment<FragmentListMessageBinding, MainViewModel>() {
    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_list_message

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        onClickListener()
    }

    private fun onClickListener() {
        binding.ivBack.setOnClickListener { popBackStack() }

        binding.ivService1.setOnClickListener {
            navigateScreen(null, R.id.messageFragment)
        }
    }

    override fun onPermissionGranted() {

    }

    override fun observerData() {

    }
}