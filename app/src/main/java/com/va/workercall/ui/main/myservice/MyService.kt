package com.va.workercall.ui.main.myservice

import android.os.Bundle
import android.view.View
import com.va.workercall.R
import com.va.workercall.common.Constant
import com.va.workercall.databinding.FragmentMyServiceBinding
import com.va.workercall.ui.base.BaseBindingFragment
import com.va.workercall.ui.main.MainViewModel

class MyService : BaseBindingFragment<FragmentMyServiceBinding, MainViewModel>() {
    private var bundle1 = Bundle()
    private var bundle2 = Bundle()

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_my_service

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        bundle1.putBoolean(Constant.EDIT_SERVICE, true)
        bundle2.putBoolean(Constant.EDIT_SERVICE, false)

        onClickListener()
    }

    private fun onClickListener() {
        binding.ivBack.setOnClickListener {
            popBackStack()
        }
        binding.ivService1.setOnClickListener {
            navigateScreen(bundle1, R.id.editServiceFragment)
        }
        binding.ivService2.setOnClickListener {
            navigateScreen(bundle1, R.id.editServiceFragment)
        }
        binding.ivService3.setOnClickListener {
            navigateScreen(bundle1, R.id.editServiceFragment)
        }
        binding.ivService4.setOnClickListener {
            navigateScreen(bundle1, R.id.editServiceFragment)
        }
        binding.ivService5.setOnClickListener {
            navigateScreen(bundle1, R.id.editServiceFragment)
        }
        binding.btnAdd.setOnClickListener {
            navigateScreen(bundle2, R.id.editServiceFragment)
        }
    }

    override fun onPermissionGranted() {
    }

    override fun observerData() {
    }
}