package com.va.workercall.ui.main.receipt

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.va.workercall.R
import com.va.workercall.common.models.Receipt
import com.va.workercall.databinding.FragmentReceiptBinding
import com.va.workercall.ui.adapter.ReceiptAdapter
import com.va.workercall.ui.base.BaseBindingFragment

class ReceiptFragment : BaseBindingFragment<FragmentReceiptBinding, ReceiptViewModel>(), ReceiptAdapter.OnReceiptListener {
    private var mReceiptList: MutableList<Receipt> = ArrayList()
    private lateinit var receiptAdapter: ReceiptAdapter

    override fun getViewModel(): Class<ReceiptViewModel> {
        return ReceiptViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_receipt

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        setupBottom()

        initTabLayout()

        initRecyclerView()

        onClickListener()
    }

    private fun setupBottom() {
        binding.viewBottomNavigation.ivHome.setImageDrawable(requireContext().getDrawable(R.drawable.ic_home_unchoose))
        binding.viewBottomNavigation.ivReceipt.setImageDrawable(requireContext().getDrawable(R.drawable.ic_receipt_choose))
        binding.viewBottomNavigation.tvReceipt.setTextColor(Color.parseColor("#FFA92E"))
    }

    private fun onClickListener() {
        binding.viewBottomNavigation.ivHome.setOnClickListener {
            popBackStack()
        }

        binding.ivCalendar.setOnClickListener {
            navigateScreen(null, R.id.calendarFragment)
        }

        binding.viewBottomNavigation.btnNoti.setOnClickListener {
            popBackStackWithInclusive(R.id.homeFragment, false)
            navigateScreen(null, R.id.notificationFragment)
        }

        binding.viewBottomNavigation.btnPersonal.setOnClickListener {
            popBackStackWithInclusive(R.id.homeFragment, false)
            navigateScreen(null, R.id.personalFragment)
        }
    }

    private fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        binding.rcvReceipt.layoutManager = linearLayoutManager
        receiptAdapter = ReceiptAdapter(requireContext(), this)
        binding.rcvReceipt.adapter = receiptAdapter

        viewModel.getListReceiptInfo()
    }

    private fun initTabLayout() {
        val tabWaiting: TabLayout.Tab = binding.tabLayoutMedia.newTab()
        tabWaiting.text = "Đang chờ"

        val tabConfirmed: TabLayout.Tab = binding.tabLayoutMedia.newTab()
        tabConfirmed.text = "Đã xác nhận"

        val tabFinish: TabLayout.Tab = binding.tabLayoutMedia.newTab()
        tabFinish.text = "Hoàn thành"

        val tabCancel: TabLayout.Tab = binding.tabLayoutMedia.newTab()
        tabCancel.text = "Đã hủy"

        binding.tabLayoutMedia.addTab(tabWaiting, 0)
        binding.tabLayoutMedia.addTab(tabConfirmed, 1)
        binding.tabLayoutMedia.addTab(tabFinish, 2)
        binding.tabLayoutMedia.addTab(tabCancel, 3)

        binding.tabLayoutMedia.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    when (tab.position) {
                        0 -> {
                            val receiptList: MutableList<Receipt> = ArrayList()
                            for (i in mReceiptList) {
                                if (i.status == 1) {
                                    receiptList.add(i)
                                }
                            }
                            receiptAdapter.setListReceipt(receiptList)
                        }
                        1 -> {
                            val receiptList: MutableList<Receipt> = ArrayList()
                            for (i in mReceiptList) {
                                if (i.status == 2) {
                                    receiptList.add(i)
                                }
                            }
                            receiptAdapter.setListReceipt(receiptList)
                        }
                        2 -> {
                            val receiptList: MutableList<Receipt> = ArrayList()
                            for (i in mReceiptList) {
                                if (i.status == 3) {
                                    receiptList.add(i)
                                }
                            }
                            receiptAdapter.setListReceipt(receiptList)
                        }
                        3 -> {
                            val receiptList: MutableList<Receipt> = ArrayList()
                            for (i in mReceiptList) {
                                if (i.status == 4) {
                                    receiptList.add(i)
                                }
                            }
                            receiptAdapter.setListReceipt(receiptList)
                        }
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    override fun onPermissionGranted() {

    }

    override fun observerData() {
        viewModel.listReceiptLiveData.observe(viewLifecycleOwner) {
            if (it != null && it.size > 0) {
                mReceiptList.clear()
                mReceiptList.addAll(it)
                val receiptList: MutableList<Receipt> = ArrayList()
                for (i in mReceiptList) {
                    if (i.status == 1) {
                        receiptList.add(i)
                    }
                }

                receiptAdapter.setListReceipt(receiptList)
            }
        }
    }

    override fun detailClick(id: Int) {
        val bundle: Bundle = Bundle()
        if (id == 123123) {
            bundle.putInt("BOOKING_NUMBER", 123123)
            navigateScreen(bundle, R.id.receiptDetailFragment)
        } else if (id == 234324) {
            bundle.putInt("BOOKING_NUMBER", 234324)
            navigateScreen(bundle, R.id.receiptDetailFragment)
        } else if (id == 456565) {
            bundle.putInt("BOOKING_NUMBER", 789898)
            navigateScreen(bundle, R.id.receiptDetailFragment)
        } else if (id == 789898) {
            bundle.putInt("BOOKING_NUMBER", 456565)
            navigateScreen(bundle, R.id.receiptDetailFragment)
        }
    }

    override fun seeRate(id: Int) {
        navigateScreen(null, R.id.rateFragment)
    }
}