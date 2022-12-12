package com.va.workercall.ui.main.workerlist

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.va.workercall.R
import com.va.workercall.databinding.FragmentWorkerListBinding
import com.va.workercall.ui.adapter.WorkerPublicAdapter
import com.va.workercall.ui.base.BaseBindingFragment

class WorkerListFragment : BaseBindingFragment<FragmentWorkerListBinding, WorkerListViewModel>(), WorkerPublicAdapter.WorkerPublicListener {
    private lateinit var workerPublicAdapter: WorkerPublicAdapter

    override fun getViewModel(): Class<WorkerListViewModel> {
        return WorkerListViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_worker_list

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        binding.rvWorkerList.layoutManager = linearLayoutManager
        workerPublicAdapter = WorkerPublicAdapter(requireContext(), this)
        binding.rvWorkerList.adapter = workerPublicAdapter

        mainViewModel.getListWorkerInfo()

        onClickListener()
    }

    private fun onClickListener() {
        binding.ivBack.setOnClickListener {
            popBackStack()
        }
    }

    override fun onPermissionGranted() {

    }

    override fun observerData() {
        mainViewModel.listWorkerLiveData.observe(viewLifecycleOwner) {
            if (it != null && it.size > 0) {
                workerPublicAdapter.setListWorker(it)
            }
        }
    }

    override fun itemClick(id: Int) {
        navigateScreen(null, R.id.workerInfoFragment)
    }
}