package com.va.workercall.ui.main.calendar

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.va.workercall.R
import com.va.workercall.databinding.FragmentCalendarBinding
import com.va.workercall.ui.base.BaseBindingFragment
import com.va.workercall.ui.main.MainViewModel
import java.util.Calendar

class CalendarFragment : BaseBindingFragment<FragmentCalendarBinding, MainViewModel>() {
    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_calendar

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        binding.calendarView.setDate(Calendar.getInstance().timeInMillis, true, true);

        onClickListener()

        setupBottom()
    }

    private fun setupBottom() {
        binding.viewBottomNavigation.ivHome.setImageDrawable(requireContext().getDrawable(R.drawable.ic_home_unchoose))
        binding.viewBottomNavigation.ivReceipt.setImageDrawable(requireContext().getDrawable(R.drawable.ic_receipt_choose))
        binding.viewBottomNavigation.tvReceipt.setTextColor(Color.parseColor("#FFA92E"))
    }

    private fun onClickListener() {

        binding.viewBottomNavigation.ivHome.setOnClickListener {
            popBackStackWithInclusive(R.id.homeFragment, false)
        }

        val bundle: Bundle = Bundle()
        binding.ivReceipt1.setOnClickListener {
            bundle.putInt("BOOKING_NUMBER", 123123)
            navigateScreen(bundle, R.id.receiptDetailFragment)
        }

        binding.ivReceipt2.setOnClickListener {
            bundle.putInt("BOOKING_NUMBER", 234324)
            navigateScreen(bundle, R.id.receiptDetailFragment)
        }

        binding.ivReceipt3.setOnClickListener {
            bundle.putInt("BOOKING_NUMBER", 456565)
            navigateScreen(bundle, R.id.receiptDetailFragment)
        }

        binding.ivReceipt4.setOnClickListener {
            bundle.putInt("BOOKING_NUMBER", 789898)
            navigateScreen(bundle, R.id.receiptDetailFragment)
        }
    }

    override fun onPermissionGranted() {

    }

    override fun observerData() {

    }
}