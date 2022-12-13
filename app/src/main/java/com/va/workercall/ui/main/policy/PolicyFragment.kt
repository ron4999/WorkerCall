package com.va.workercall.ui.main.policy

import android.os.Bundle
import android.view.View
import com.va.workercall.R
import com.va.workercall.databinding.FragmentPolicyBinding
import com.va.workercall.ui.base.BaseBindingFragment
import com.va.workercall.ui.main.MainActivity
import com.va.workercall.ui.main.MainViewModel

class PolicyFragment : BaseBindingFragment<FragmentPolicyBinding, MainViewModel>() {
    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_policy

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        initData()

        onClickListener()
    }

    private fun initData() {
        if (MainActivity.isWorker) {
            binding.tvPolicyContent1.setText("Thợ được phép hủy đơn ít nhất 4 tiếng trước giờ ghi trên đơn hàng")
            binding.tvPolicyContent2.setText("Nếu hủy 10 đơn hệ thống sẽ tự động khóa tài khoản vô thời hạn")
            binding.tvPolicyContent3.visibility = View.GONE
            binding.tvThirdDot.visibility = View.GONE
        }
    }

    private fun onClickListener() {
        binding.ivBack.setOnClickListener {
            popBackStack()
        }
    }

    override fun onPermissionGranted() {

    }

    override fun observerData() {

    }
}