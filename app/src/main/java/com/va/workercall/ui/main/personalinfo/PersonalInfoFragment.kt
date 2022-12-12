package com.va.workercall.ui.main.personalinfo

import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Toast
import com.va.workercall.R
import com.va.workercall.databinding.FragmentPersonalInfoBinding
import com.va.workercall.ui.base.BaseBindingFragment
import com.va.workercall.ui.main.MainViewModel

class PersonalInfoFragment : BaseBindingFragment<FragmentPersonalInfoBinding, MainViewModel>() {
    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_personal_info

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        binding.edtName.inputType = InputType.TYPE_NULL
        binding.edtBirthday.inputType = InputType.TYPE_NULL
        binding.edtPhoneNumber.inputType = InputType.TYPE_NULL
        binding.edtPlace.inputType = InputType.TYPE_NULL
        binding.edtId.inputType = InputType.TYPE_NULL

        onClickListener()
    }

    private fun onClickListener() {
        binding.ivBack.setOnClickListener {
            popBackStack()
        }

        binding.tvConfirmChange.setOnClickListener {
            Toast.makeText(requireContext(), "Thay đổi thành công", Toast.LENGTH_SHORT).show()
            popBackStack()
        }
    }

    override fun onPermissionGranted() {

    }

    override fun observerData() {

    }
}