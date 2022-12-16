package com.va.workercall.ui.main.takephoto

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.va.workercall.R
import com.va.workercall.databinding.FragmentTakephotoBinding
import com.va.workercall.ui.base.BaseBindingFragment
import com.va.workercall.ui.main.MainViewModel

class TakePhotoFragment : BaseBindingFragment<FragmentTakephotoBinding, MainViewModel>() {
    private var isCapturePortrait = false
    private var isDocFirst = false
    private var isDone = false

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_takephoto

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        binding.btnContinue.setOnClickListener {
            if (!isCapturePortrait) {
                showCamera(true)
                Handler(Looper.getMainLooper()).postDelayed(Runnable {
                    if (isAdded) {
                        binding.layoutCamera.ivImg.visibility = View.VISIBLE
                    }
                }, 2000)
                Handler(Looper.getMainLooper()).postDelayed(Runnable {
                    if (isAdded) {
                        isCapturePortrait = true
                        binding.tvText.visibility = View.GONE
                        binding.ivImg.setImageResource(R.drawable.img_person_big)
                        binding.ivBack.visibility = View.VISIBLE
                        binding.tvTitle.visibility = View.VISIBLE

                        showCamera(false)
                    }
                }, 4000)
            } else {
                binding.layoutDoc.root.visibility = View.VISIBLE
            }

        }
        binding.layoutCamera.ivCancel.setOnClickListener {
            showCamera(false)
        }
        binding.layoutDoc.btnContinue.setOnClickListener {
            if (!isDone) {
                binding.layoutDoc.root.visibility = View.GONE
                showCamera(true)
                if (!isDocFirst) {
                    binding.layoutCamera.ivImg.setImageResource(R.drawable.ic_cmt)
                } else {
                    binding.layoutCamera.ivImg.setImageResource(R.drawable.ic_cmt_2)
                }
                Handler(Looper.getMainLooper()).postDelayed(Runnable {
                    if (isAdded) {
                        binding.layoutCamera.ivImg.visibility = View.VISIBLE
                    }
                }, 2000)
                Handler(Looper.getMainLooper()).postDelayed(Runnable {
                    if (isAdded) {
                        binding.layoutDoc.root.visibility = View.VISIBLE
                        binding.layoutDoc.ivDocCmt.visibility = View.VISIBLE
                        binding.layoutDoc.tvTitle.visibility = View.GONE
                        if (isDocFirst) {
                            binding.layoutDoc.ivDocCmt2.visibility = View.VISIBLE
                            isDone = true
                        }
                        binding.layoutDoc.imgDoc.visibility = View.GONE
                        isDocFirst = true
                    }
                }, 4000)
            } else {
                navigateScreen(null, R.id.fragmentSetupWorker)
            }

        }
    }

    private fun showCamera(isShow : Boolean) {
        if (isShow) {
            binding.layoutCamera.root.visibility = View.VISIBLE
        } else {
            binding.layoutCamera.root.visibility = View.GONE
        }
    }

    override fun onPermissionGranted() {
    }

    override fun observerData() {
    }
}