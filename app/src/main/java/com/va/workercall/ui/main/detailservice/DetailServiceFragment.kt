package com.va.workercall.ui.main.detailservice

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.va.workercall.R
import com.va.workercall.databinding.FragmentDetailServiceBinding
import com.va.workercall.ui.base.BaseBindingFragment
import com.va.workercall.ui.dialog.DialogSendRequest
import com.va.workercall.ui.main.MainActivity
import com.va.workercall.ui.main.MainViewModel

class DetailServiceFragment : BaseBindingFragment<FragmentDetailServiceBinding, MainViewModel>(),DialogSendRequest.OnClickListener {
    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_detail_service

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        binding.ivMess.setOnClickListener{
            navigateScreen(null, R.id.messageFragment)
        }
        binding.tvConfirm.setOnClickListener {
            MainActivity.isAccept = true
            val dialogSendRequest: DialogSendRequest = DialogSendRequest(this)
            dialogSendRequest.show(childFragmentManager, null)
        }
        binding.tvReject.setOnClickListener {
            Toast.makeText(requireContext(), "Bạn đã từ chối yêu cầu", Toast.LENGTH_SHORT).show()
            popBackStack()
        }
    }

    override fun onPermissionGranted() {
    }

    override fun observerData() {
    }

    override fun onClickLater() {
        popBackStack()
    }

    override fun onClickMessage() {
        popBackStack()
        navigateScreen(null, R.id.messageFragment)
    }
}