package com.va.workercall.ui.main.privatesetting

import android.os.Bundle
import android.view.View
import com.va.workercall.R
import com.va.workercall.databinding.FragmentPrivateSettingBinding
import com.va.workercall.ui.base.BaseBindingFragment
import com.va.workercall.ui.main.MainViewModel

class PrivateSettingFragment : BaseBindingFragment<FragmentPrivateSettingBinding, MainViewModel>() {
    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_private_setting

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        onClickListener()
    }

    private fun onClickListener() {
        binding.ivBack.setOnClickListener {
            popBackStack()
        }

        binding.tvPrivateSetting2.setOnClickListener {
            navigateScreen(null, R.id.systemSettingFragment)
        }
    }

    override fun onPermissionGranted() {
    }

    override fun observerData() {
    }
}