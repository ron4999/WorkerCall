package com.va.workercall.ui.main.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.va.workercall.R
import com.va.workercall.databinding.FragmentSplashBinding
import com.va.workercall.ui.base.BaseBindingFragment
import com.va.workercall.ui.main.MainViewModel

class SplashFragment : BaseBindingFragment<FragmentSplashBinding, MainViewModel>() {
    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_splash

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        Handler(Looper.getMainLooper()).postDelayed({
            navigateScreen(null, R.id.loginFragment)
        }, 3000)
    }

    override fun onPermissionGranted() {

    }

    override fun observerData() {

    }
}