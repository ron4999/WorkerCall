package com.va.workercall.ui.main.home

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Orientation
import com.va.workercall.R
import com.va.workercall.databinding.FragmentHomeBinding
import com.va.workercall.ui.adapter.WorkerPublicAdapter
import com.va.workercall.ui.base.BaseBindingFragment
import com.va.workercall.utils.Utils
import timber.log.Timber

class HomeFragment : BaseBindingFragment<FragmentHomeBinding, HomeViewModel>(), WorkerPublicAdapter.WorkerPublicListener {
    private lateinit var workerPublicAdapter: WorkerPublicAdapter

    override fun getViewModel(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_home

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = RecyclerView.HORIZONTAL
        binding.rvPublicList.layoutManager = linearLayoutManager
        workerPublicAdapter = WorkerPublicAdapter(requireContext(), this)
        binding.rvPublicList.adapter = workerPublicAdapter

        binding.viewBottomNavigation.tvHome.setTextColor(Color.parseColor("#FAC227"))

        mainViewModel.getListWorkerInfo()

        onClickListener()

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            mBackPressedCallback
        )
    }

    private val mBackPressedCallback: OnBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        }


    private fun onClickListener() {
        binding.tvCreateService.setOnClickListener {
            navigateScreen(null, R.id.createServiceFragment)
        }

        binding.viewBottomNavigation.btnReceipt.setOnClickListener {
            navigateScreen(null, R.id.receiptFragment)
        }

        binding.tvWashingMachine.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("SERVICE_TYPE", 1)
            navigateScreen(bundle, R.id.serviceFragment)
        }

        binding.tvBroom.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("SERVICE_TYPE", 2)
            navigateScreen(bundle, R.id.serviceFragment)
        }

        binding.tvBath.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("SERVICE_TYPE", 3)
            navigateScreen(bundle, R.id.serviceFragment)
        }
        binding.tvElectric.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("SERVICE_TYPE", 4)
            navigateScreen(bundle, R.id.serviceFragment)
        }

        binding.ivMessage.setOnClickListener {
            navigateScreen(null, R.id.listMessageFragment)
        }

        binding.viewBottomNavigation.btnPersonal.setOnClickListener {
            navigateScreen(null, R.id.personalFragment)
        }

        binding.edtSearchService.setOnClickListener {
            navigateScreen(null, R.id.searchFragment)
        }

        binding.viewBottomNavigation.btnNoti.setOnClickListener {
            navigateScreen(null, R.id.notificationFragment)
        }

        binding.tvGoToDetail.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("BOOKING_NUMBER", 789898)
            navigateScreen(bundle, R.id.receiptDetailFragment)
        }
    }

    override fun onPermissionGranted() {

    }

    override fun observerData() {
        Timber.e("abc: observerData")
        mainViewModel.listWorkerLiveData.observe(viewLifecycleOwner) {
            if (it != null && it.size > 0) {
                Timber.e("abc: size = ${it.size}")
                workerPublicAdapter.setListWorker(it)
            }
        }
    }

    override fun itemClick(id: Int) {
        navigateScreen(null, R.id.workerInfoFragment)
    }
}