package com.va.workercall.ui.main.notification

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.va.workercall.R
import com.va.workercall.databinding.FragmentNotificationBinding
import com.va.workercall.ui.base.BaseBindingFragment
import com.va.workercall.ui.main.MainViewModel

class NotificationFragment : BaseBindingFragment<FragmentNotificationBinding, MainViewModel>() {
    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_notification

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
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
            popBackStackWithInclusive(R.id.homeFragment, false)
        }

        binding.viewBottomNavigation.btnReceipt.setOnClickListener {
            popBackStackWithInclusive(R.id.homeFragment, false)
            navigateScreen(null, R.id.receiptFragment)
        }

        binding.viewBottomNavigation.btnPersonal.setOnClickListener {
            popBackStackWithInclusive(R.id.homeFragment, false)
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