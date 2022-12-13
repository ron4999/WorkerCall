package com.va.workercall.ui.main.findingworker

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.va.workercall.R
import com.va.workercall.databinding.FragmentFindingWorkerBinding
import com.va.workercall.ui.base.BaseBindingFragment
import com.va.workercall.ui.main.MainViewModel

class FindingWorkerFragment : BaseBindingFragment<FragmentFindingWorkerBinding, MainViewModel>() {
    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_finding_worker

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            if (isAdded) {
                binding.tvFinding.visibility = View.INVISIBLE
                binding.tvFinishFinding.visibility = View.VISIBLE
                binding.tvFindingWorker.text = "Tìm thấy 4 hồ sơ gần bạn"
                binding.tvTitleFinding.text = "Chúc mừng"
            }
        }, 3000)

        binding.tvPlaceFinding.text = "45 Trần Thái Tông"

        onClickListener()
    }

    private fun onClickListener() {
        binding.tvFinishFinding.setOnClickListener {
            navigateScreen(null, R.id.workerListFragment)
        }

        binding.ivBack.setOnClickListener {
            popBackStack()
        }
    }

    override fun onPermissionGranted() {

    }

    override fun observerData() {

    }
}