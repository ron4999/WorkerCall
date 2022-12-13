package com.va.workercall.ui.main.message

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.va.workercall.R
import com.va.workercall.common.Constant
import com.va.workercall.databinding.FragmentMessageBinding
import com.va.workercall.ui.base.BaseBindingFragment
import com.va.workercall.ui.main.MainActivity
import com.va.workercall.ui.main.MainViewModel

class MessageFragment : BaseBindingFragment<FragmentMessageBinding, MainViewModel>() {
    private var isAdd = false

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_message

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        initData()

        onClickListener()
    }

    private fun initData() {
        if (MainActivity.isWorker) {
            binding.tvName.setText("Đỗ Phương")
            binding.ivFakeMess1.visibility = View.GONE
            binding.ivFakeMess2.visibility = View.GONE
            binding.cvRequest.visibility = View.GONE
            binding.layoutMessWorker.root.visibility = View.VISIBLE
        }
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
        binding.ivAdd.setOnClickListener {
            if (MainActivity.isWorker) {
                if (isAdd) {
                    binding.layoutBottom.root.visibility = View.GONE
                } else {
                    binding.layoutBottom.root.visibility = View.VISIBLE
                }
                isAdd = !isAdd
            }
        }
        binding.layoutBottom.ivCreateService.setOnClickListener {
            var bundle = Bundle()
            bundle.putBoolean(Constant.EDIT_SERVICE, false)
            navigateScreen(bundle, R.id.editServiceFragment)
        }
        binding.layoutBottom.ivService.setOnClickListener {
            binding.layoutMessWorker.mess3.visibility = View.VISIBLE
            Handler(Looper.getMainLooper()).postDelayed({
                binding.layoutMessWorker.mess4.visibility = View.VISIBLE
            }, 3000)
            Handler(Looper.getMainLooper()).postDelayed({
                binding.toast.visibility = View.VISIBLE
            }, 3500)
        }
    }

    override fun onPermissionGranted() {

    }

    override fun observerData() {

    }
}