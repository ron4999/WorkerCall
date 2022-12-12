package com.va.workercall.ui.main.editservice

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.va.workercall.R
import com.va.workercall.common.Constant
import com.va.workercall.databinding.FragmentEditServiceBinding
import com.va.workercall.ui.base.BaseBindingFragment
import com.va.workercall.ui.main.MainViewModel

class EditServiceFragment : BaseBindingFragment<FragmentEditServiceBinding, MainViewModel>() {
    private var isEdit = false

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_edit_service

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        arguments?.let {
            isEdit = it.getBoolean(Constant.EDIT_SERVICE)
            if (!isEdit) {
                binding.tvTitle.setText("Tạo dịch vụ")
                binding.edtName.setText("Nhập tên dịch vụ")
                binding.edtPrice.setText("Nhập giá")
                binding.edtContent.setText("Nội dung dịch vụ")
                binding.edtJob.setText("Công việc thực hiện")
                binding.edtInfo.setText("Thông tin dịch vụ")
                binding.edtInsurance.setText("Thông tin bảo hành")
                binding.tvEdit.setText("Tạo dịch vụ")
            }
        }
        onClickListener()
    }

    private fun onClickListener() {
        binding.ivBack.setOnClickListener {
            popBackStack()
        }
        binding.tvEdit.setOnClickListener {
            if (!isEdit) {
                Toast.makeText(requireContext(), "Đã tạo dịch vụ!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Đã sửa dịch vụ!", Toast.LENGTH_SHORT).show()
            }
            popBackStack()
        }
    }

    override fun onPermissionGranted() {
    }

    override fun observerData() {
    }
}