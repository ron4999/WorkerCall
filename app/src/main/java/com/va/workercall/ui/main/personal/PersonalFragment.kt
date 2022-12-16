package com.va.workercall.ui.main.personal

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.va.workercall.R
import com.va.workercall.common.Constant
import com.va.workercall.databinding.FragmentPersonalBinding
import com.va.workercall.ui.base.BaseBindingFragment
import com.va.workercall.ui.main.MainActivity
import com.va.workercall.ui.main.MainActivity.Companion.isWorker
import com.va.workercall.ui.main.MainViewModel

class PersonalFragment : BaseBindingFragment<FragmentPersonalBinding, MainViewModel>() {
    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_personal

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        initData()

        setupBottom()

        onClickListener()
    }

    private fun initData() {
        if (isWorker) {
            binding.ivAvatar.setImageResource(R.drawable.ic_image_person_1)
            binding.tvName.setText("Nguyễn Minh Cường")
            binding.tvFavoriteWorker.visibility = View.GONE
            binding.viewHorizontal1.visibility = View.GONE
        }
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

        binding.viewBottomNavigation.btnNoti.setOnClickListener {
            if (!isWorker) {
                popBackStackWithInclusive(R.id.homeFragment, false)
            } else {
                popBackStackWithInclusive(R.id.homeWorkerFragment, false)
            }
            navigateScreen(null, R.id.notificationFragment)
        }

        binding.tvPolicy.setOnClickListener {
            navigateScreen(null, R.id.policyFragment)
        }

        binding.tvPersonalInfo.setOnClickListener {
            if (MainActivity.isWorker) {
                navigateScreen(null, R.id.fragmentSetupWorker)
            } else {
                navigateScreen(null, R.id.personalInfoFragment)
            }
        }

        binding.tvFavoriteWorker.setOnClickListener {
            navigateScreen(null, R.id.favouriteWorkerFragment)
        }

        binding.tvPayment.setOnClickListener {
            navigateScreen(null, R.id.paymentFragment)
        }

        binding.tvAccountSetting.setOnClickListener {
            navigateScreen(null, R.id.accountSettingFragment)
        }
    }

    private fun setupBottom() {
        binding.viewBottomNavigation.ivHome.setImageDrawable(requireContext().getDrawable(R.drawable.ic_home_unchoose))
        binding.viewBottomNavigation.ivPersonal.setImageDrawable(requireContext().getDrawable(R.drawable.ic_personal_info))
        binding.viewBottomNavigation.tvPersonal.setTextColor(Color.parseColor("#FFA92E"))
    }

    override fun onPermissionGranted() {

    }

    override fun observerData() {

    }

}