package com.va.workercall.ui.main.favouriteworker

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.va.workercall.R
import com.va.workercall.common.models.Receipt
import com.va.workercall.common.models.WorkerInfo
import com.va.workercall.databinding.FragmentFavouriteWorkerBinding
import com.va.workercall.ui.adapter.ReceiptAdapter
import com.va.workercall.ui.adapter.WorkerFavouriteAdapter
import com.va.workercall.ui.base.BaseBindingFragment
import com.va.workercall.ui.main.MainViewModel

class FavouriteWorkerFragment : BaseBindingFragment<FragmentFavouriteWorkerBinding, MainViewModel>(), WorkerFavouriteAdapter.WorkerFavListener {
    private var mFavouriteWorkerList: MutableList<WorkerInfo> = ArrayList()
    private lateinit var workerFavAdapter: WorkerFavouriteAdapter

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_favourite_worker

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        initRecyclerView()

        onClickListener()
    }

    private fun onClickListener() {
        binding.ivBack.setOnClickListener {
            popBackStack()
        }
    }

    private fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        binding.rcvFavouriteWorker.layoutManager = linearLayoutManager
        workerFavAdapter = WorkerFavouriteAdapter(requireContext(), this)
        binding.rcvFavouriteWorker.adapter = workerFavAdapter

        mainViewModel.getListWorkerInfo()
    }

    override fun onPermissionGranted() {

    }

    override fun observerData() {
        mainViewModel.listWorkerLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                workerFavAdapter.setListWorker(it)
            }
        }
    }

    override fun onRequestClick(id: Int) {

    }

    override fun onInfoClick(id: Int) {
        navigateScreen(null, R.id.workerInfoFragment)
    }
}