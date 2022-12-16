package com.va.workercall.ui.main.notification

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.va.workercall.R
import com.va.workercall.databinding.FragmentNotificationBinding
import com.va.workercall.ui.base.BaseBindingFragment
import com.va.workercall.ui.dialog.DialogSendRequest
import com.va.workercall.ui.main.MainActivity
import com.va.workercall.ui.main.MainActivity.Companion.isWorker
import com.va.workercall.ui.main.MainViewModel

class NotificationFragment : BaseBindingFragment<FragmentNotificationBinding, MainViewModel>(),
    DialogSendRequest.OnClickListener {
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
            binding.tvMessage.setText("Đồng ý")
            binding.tvDetail.setText("Chi tiết")
            if (MainActivity.isAccept) {
                binding.tvMessage.visibility = View.GONE
                binding.tvDetail.visibility = View.GONE
                binding.cslAcceptRequest.setBackgroundColor(Color.WHITE)
                binding.cslCalendar.visibility = View.VISIBLE
                binding.cslNoti1.visibility = View.VISIBLE
            }
            binding.ivMess.visibility = View.VISIBLE
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
            if (isWorker) {
                MainActivity.isAccept = true
                val dialogSendRequest: DialogSendRequest = DialogSendRequest(this)
                dialogSendRequest.show(childFragmentManager, null)
            } else {
                navigateScreen(null, R.id.messageFragment)
            }
        }

        binding.tvDetail.setOnClickListener {
            if (!isWorker) {
                val bundle = Bundle()
                bundle.putInt("BOOKING_NUMBER", 234324)
                navigateScreen(bundle, R.id.receiptDetailFragment)
            } else {
                navigateScreen(null, R.id.detailServiceFragment)
            }

        }
        binding.ivMess.setOnClickListener {
            navigateScreen(null, R.id.listMessageFragment)
        }
        binding.tvDetail2.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("BOOKING_NUMBER", 234324)
            navigateScreen(bundle, R.id.receiptDetailFragment)
        }
        binding.tvMessage2.setOnClickListener {
            navigateScreen(null, R.id.fragmentMap)
        }
    }

    override fun onPermissionGranted() {

    }

    override fun observerData() {

    }

    override fun onClickLater() {
        popBackStackWithInclusive(R.id.homeWorkerFragment, false)
    }

    override fun onClickMessage() {
        navigateScreen(null, R.id.messageFragment)
    }
}