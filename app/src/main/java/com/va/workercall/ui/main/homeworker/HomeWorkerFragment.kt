package com.va.workercall.ui.main.homeworker

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import com.va.workercall.R
import com.va.workercall.common.Constant
import com.va.workercall.databinding.FragmentHomeWorkerBinding
import com.va.workercall.ui.base.BaseBindingActivity
import com.va.workercall.ui.base.BaseBindingFragment
import com.va.workercall.ui.main.MainViewModel

class HomeWorkerFragment : BaseBindingFragment<FragmentHomeWorkerBinding, MainViewModel>() {
    private var bundle1 = Bundle()
    private var bundle2 = Bundle()

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_home_worker

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {

        onClickListener()

        bundle1.putBoolean(Constant.EDIT_SERVICE, true)
        bundle2.putBoolean(Constant.EDIT_SERVICE, false)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            mBackPressedCallback
        )
    }

    private fun onClickListener() {
        binding.tvSeeMore.setOnClickListener {
            navigateScreen(null, R.id.myService)
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

    private val mBackPressedCallback: OnBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        }

    override fun onPermissionGranted() {
    }

    override fun observerData() {
    }
}