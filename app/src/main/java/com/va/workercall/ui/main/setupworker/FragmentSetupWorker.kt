package com.va.workercall.ui.main.setupworker

import android.os.Bundle
import android.view.View
import com.va.workercall.R
import com.va.workercall.databinding.FragmentSetupWorkerBinding
import com.va.workercall.ui.base.BaseBindingFragment
import com.va.workercall.ui.main.MainViewModel

class FragmentSetupWorker : BaseBindingFragment<FragmentSetupWorkerBinding, MainViewModel>() {
    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_setup_worker

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        binding.btnChange.setOnClickListener{
            var bundle = Bundle()
            bundle.putBoolean("REGISTER_WORKER", true)
            navigateScreen(bundle, R.id.registerFragment)
        }
    }

    override fun onPermissionGranted() {
    }

    override fun observerData() {
    }
}