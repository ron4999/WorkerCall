package com.va.workercall.ui.main.service

import android.os.Bundle
import android.view.View
import com.va.workercall.R
import com.va.workercall.databinding.FragmentServiceBinding
import com.va.workercall.ui.base.BaseBindingFragment
import com.va.workercall.ui.main.MainViewModel

class ServiceFragment : BaseBindingFragment<FragmentServiceBinding, MainViewModel>() {
    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_service

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        arguments?.let {
            val type = it.getInt("SERVICE_TYPE")
            when (type) {
                1 -> {
                    binding.tvTitle.text = "Điện lạnh"
                }
                2 -> {
                    binding.tvTitle.text = "Vệ sinh"
                }
                3 -> {
                    binding.tvTitle.text = "Ống nước"
                }
                4 -> {
                    binding.tvTitle.text = "Điện"
                }
            }
        }
        onClickListener()
    }

    private fun onClickListener() {
        binding.ivBack.setOnClickListener {
            popBackStack()
        }

        binding.ivService1.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("SERVICE_NO", 1)
            navigateScreen(bundle, R.id.createServiceFragment)
        }

        binding.ivService2.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("SERVICE_NO", 2)
            navigateScreen(bundle, R.id.createServiceFragment)
        }

        binding.ivService3.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("SERVICE_NO", 3)
            navigateScreen(bundle, R.id.createServiceFragment)
        }

        binding.ivService4.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("SERVICE_NO", 4)
            navigateScreen(bundle, R.id.createServiceFragment)
        }

        binding.ivService5.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("SERVICE_NO", 5)
            navigateScreen(bundle, R.id.createServiceFragment)
        }
    }

    override fun onPermissionGranted() {

    }

    override fun observerData() {

    }
}