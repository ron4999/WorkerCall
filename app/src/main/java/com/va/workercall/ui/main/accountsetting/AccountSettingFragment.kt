package com.va.workercall.ui.main.accountsetting

import android.os.Bundle
import android.view.View
import com.va.workercall.R
import com.va.workercall.databinding.FragmentAccountSettingBinding
import com.va.workercall.ui.base.BaseBindingFragment
import com.va.workercall.ui.main.MainViewModel

class AccountSettingFragment : BaseBindingFragment<FragmentAccountSettingBinding, MainViewModel>() {
    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_account_setting

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        onClickListener()
    }

    private fun onClickListener() {
        binding.tvNotiSetting.setOnClickListener {
            navigateScreen(null, R.id.notiSettingFragment)
        }

        binding.tvPrivateSetting.setOnClickListener {
            navigateScreen(null, R.id.privateSettingFragment)
        }
    }

    override fun onPermissionGranted() {
    }

    override fun observerData() {
    }
}