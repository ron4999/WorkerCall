package com.va.workercall.ui.main.notification

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.va.workercall.R
import com.va.workercall.common.Constant
import com.va.workercall.databinding.FragmentNotificationBinding
import com.va.workercall.ui.base.BaseBindingFragment
import com.va.workercall.ui.main.MainActivity.Companion.isWorker
import com.va.workercall.ui.main.MainViewModel

class NotificationFragment : BaseBindingFragment<FragmentNotificationBinding, MainViewModel>() {
    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_notification

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        if (isWorker) {
            binding.tvTitle1.setText("Yêu cầu mới")
            binding.tvContent1.setText("Đỗ Phương đã gửi đến bạn yêu cầu ...")
            binding.ivNotiRound1.setImageResource(R.drawable.img_ava_personal)
//            binding.ivMess.visibility = View.VISIBLE
        }

        setupBottom()

        onClickListener()
    }

    private fun setupBottom() {
        binding.viewBottomNavigation.ivHome.setImageDrawable(requireContext().getDrawable(R.drawable.ic_home_unchoose))
        binding.viewBottomNavigation.ivNoti.setImageDrawable(requireContext().getDrawable(R.drawable.ic_noti_choose))
        binding.viewBottomNavigation.tvNoti.setTextColor(Color.parseColor("#FFA92E"))
    }

    private fun onClickListener() {
        binding.viewBottomNavigation.btnHome.setOnClickListener {
            if (!isWorker) {
                popBackStackWithInclusive(R.id.homeFragment, false)
            } else {
                popBackStackWithInclusive(R.id.homeWorkerFragment, false)
            }
        }

        binding.viewBottomNavigation.btnReceipt.setOnClickListener {
            if (!isWorker) {
                popBackStackWithInclusive(R.id.homeFragment, false)
            } else {
                popBackStackWithInclusive(R.id.homeWorkerFragment, false)
            }
            navigateScreen(null, R.id.receiptFragment)
        }

        binding.viewBottomNavigation.btnPersonal.setOnClickListener {
            if (!isWorker) {
                popBackStackWithInclusive(R.id.homeFragment, false)
            } else {
                popBackStackWithInclusive(R.id.homeWorkerFragment, false)
            }
            navigateScreen(null, R.id.personalFragment)
        }

        binding.tvMessage.setOnClickListener {
            navigateScreen(null, R.id.messageFragment)
        }

        binding.tvDetail.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("BOOKING_NUMBER", 234324)
            navigateScreen(bundle, R.id.receiptDetailFragment)
        }
    }

    override fun onPermissionGranted() {

    }

    override fun observerData() {

    }
}