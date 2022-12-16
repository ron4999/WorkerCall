package com.va.workercall.ui.main.map

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.doOnTextChanged
import com.va.workercall.R
import com.va.workercall.common.Constant
import com.va.workercall.databinding.FragmentMapBinding
import com.va.workercall.ui.base.BaseBindingFragment
import com.va.workercall.ui.dialog.DialogSendRequest
import com.va.workercall.ui.main.MainActivity
import com.va.workercall.ui.main.MainViewModel

class FragmentMap : BaseBindingFragment<FragmentMapBinding, MainViewModel>(), DialogSendRequest.OnClickListener {
    private var isShowCall = false
    private var isCreateService = false

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_map

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        onClickListener()
        onTextChangeListener()
        if (MainActivity.isToLocation) {
            binding.layoutBottom1.root.visibility = View.GONE
            binding.layoutBottom2.root.visibility = View.VISIBLE
        }
        if (MainActivity.isWorking) {
            binding.layoutBottom2.root.visibility = View.GONE
            binding.layoutBottom1.root.visibility = View.GONE
            binding.layoutBottom3.root.visibility = View.VISIBLE
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            mBackPressedCallback
        )
    }

    private fun enableBtnSend(isEnable : Boolean) {
        if (isEnable) {
            binding.layoutBottom4.btnSend.setBackgroundResource(R.drawable.custom_bg_ffa92e_round_16)
            binding.layoutBottom4.btnSend.isClickable = true
            binding.layoutBottom4.btnSend.isEnabled = true
        } else {
            binding.layoutBottom4.btnSend.setBackgroundResource(R.drawable.custom_bg_cecece)
            binding.layoutBottom4.btnSend.isClickable = false
            binding.layoutBottom4.btnSend.isEnabled = false
        }
    }

    private fun onTextChangeListener() {
        binding.layoutBottom4.edtContent.doOnTextChanged { text, start, before, count ->
            if (text != null) {
                if (text.isNotEmpty()) {
                    if (binding.layoutBottom4.edtPrice.text.isEmpty()) {
                        enableBtnSend(false)
                    } else {
                        enableBtnSend(true)
                    }
                } else {
                    enableBtnSend(false)
                }
            }
        }
        binding.layoutBottom4.edtPrice.doOnTextChanged { text, start, before, count ->
            if (text != null) {
                if (text.isNotEmpty()) {
                    if (binding.layoutBottom4.edtContent.text.isEmpty()) {
                        enableBtnSend(false)
                    } else {
                        enableBtnSend(true)
                    }
                } else {
                    enableBtnSend(false)
                }
            }
        }
    }

    private var mBackPressedCallback: OnBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                backPress()
            }
        }

    private fun backPress() {
        if (isShowCall) {
            isShowCall = false
            showLayoutCall(false)
        } else if (isCreateService) {
            isCreateService = false
            binding.layoutBottom4.root.visibility = View.GONE
            binding.layoutBottom3.root.visibility = View.VISIBLE
        } else{
            mBackPressedCallback.isEnabled = false
            requireActivity().onBackPressed()
        }
    }


    private fun onClickListener() {
        binding.ivBack.setOnClickListener {
            backPress()
        }

        binding.layoutBottom1.ivMess.setOnClickListener {
            navigateScreen(null, R.id.messageFragment)
        }
        binding.layoutBottom1.ivPhone.setOnClickListener {
            isShowCall = true
            showLayoutCall(true)
        }
        binding.layoutCall.ivCancel.setOnClickListener {
            isShowCall = false
            showLayoutCall(false)
        }
        binding.layoutBottom1.btnGo.setOnClickListener {
            Handler(Looper.getMainLooper()).postDelayed({
                if (isAdded) {
                    binding.layoutBottom1.root.visibility = View.GONE
                    binding.layoutBottom2.root.visibility = View.VISIBLE
                    MainActivity.isToLocation = true
                }
            }, 3000)
        }
        binding.layoutBottom2.ivMess.setOnClickListener{
            navigateScreen(null, R.id.messageFragment)
        }
        binding.layoutBottom2.ivPhone.setOnClickListener{
            isShowCall = true
            showLayoutCall(true)
        }
        binding.layoutBottom2.btnStart.setOnClickListener {
            MainActivity.isWorking = true
            MainActivity.isToLocation = false
            binding.layoutBottom2.root.visibility = View.GONE
            binding.layoutBottom3.root.visibility = View.VISIBLE
        }

        binding.layoutBottom3.tvAddJob.setOnClickListener{
            isCreateService = true
            binding.layoutBottom3.root.visibility = View.GONE
            binding.layoutBottom4.root.visibility = View.VISIBLE
        }
        binding.layoutBottom3.tvFinish.setOnClickListener{
            MainActivity.isWorking = false
            val dialogSendRequest: DialogSendRequest = DialogSendRequest(this)
            dialogSendRequest.setFinish(true)
            dialogSendRequest.show(childFragmentManager, null)
        }

         binding.layoutBottom4.btnSend.setOnClickListener{
             Toast.makeText(requireContext(), "Đã gửi khách hàng", Toast.LENGTH_SHORT).show()
             backPress()
         }
    }

    private fun showLayoutCall(isShow: Boolean) {
        if (isShow) {
            binding.layoutCall.root.visibility = View.VISIBLE
        } else {
            binding.layoutCall.root.visibility = View.GONE
        }
    }

    override fun onPermissionGranted() {
    }

    override fun observerData() {
    }

    override fun onClickLater() {
        navigateScreen(null, R.id.homeWorkerFragment)
    }

    override fun onClickMessage() {
        var bundle = Bundle()
        bundle.putInt("BOOKING_NUMBER", 789898)
        bundle.putBoolean("TO_HOME", true)
        navigateScreen(bundle, R.id.receiptDetailFragment)
    }
}