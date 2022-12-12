package com.va.workercall.ui.main.search

import android.graphics.Color
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.va.workercall.R
import com.va.workercall.common.models.WorkerInfo
import com.va.workercall.databinding.FragmentSearchBinding
import com.va.workercall.ui.adapter.WorkerPublicAdapter
import com.va.workercall.ui.adapter.WorkerWithRequestAdapter
import com.va.workercall.ui.base.BaseBindingFragment
import com.va.workercall.ui.main.MainViewModel

class SearchFragment : BaseBindingFragment<FragmentSearchBinding, MainViewModel>(), WorkerWithRequestAdapter.WorkerRequestListener {
    private lateinit var workerRequestAdapter: WorkerWithRequestAdapter
    private var isClickNearMe = false
    private var isClickFeedback = false
    private var isClickPublic = false
    private var isClickPrice = false
    private var listWorker: MutableList<WorkerInfo> = ArrayList()

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_search

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        binding.rvWorkerList.layoutManager = linearLayoutManager
        workerRequestAdapter = WorkerWithRequestAdapter(requireContext(), this)
        binding.rvWorkerList.adapter = workerRequestAdapter

        mainViewModel.getListWorkerInfo()

        onClickListener()
    }

    private fun onClickListener() {
        binding.ivBack.setOnClickListener {
            popBackStack()
        }

        binding.tvNearMe.setOnClickListener {
            binding.tvPrice.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down, 0)

            if (!isClickNearMe) {
                binding.tvNearMe.setBackgroundResource(R.drawable.custom_bg_ffa92e_round_8)
                isClickNearMe = true
                binding.tvNearMe.setTextColor(Color.parseColor("#FFFFFF"))

                binding.tvFeedback.setBackgroundResource(R.drawable.custom_bg_f7f7f9_round_8)
                binding.tvPublic.setBackgroundResource(R.drawable.custom_bg_f7f7f9_round_8)
                binding.tvPrice.setBackgroundResource(R.drawable.custom_bg_f7f7f9_round_8)
                binding.tvFeedback.setTextColor(Color.parseColor("#7D848D"))
                binding.tvPublic.setTextColor(Color.parseColor("#7D848D"))
                binding.tvPrice.setTextColor(Color.parseColor("#7D848D"))
                isClickFeedback = false
                isClickPublic = false
                isClickPrice = false

                if (listWorker.size > 0) {
                    val listChange: MutableList<WorkerInfo> = ArrayList()
                    listChange.add(listWorker[1])
                    listChange.add(listWorker[0])
                    listChange.add(listWorker[2])
                    listChange.add(listWorker[3])

                    workerRequestAdapter.setListWorker(listChange)
                }

            } else {
                binding.tvNearMe.setTextColor(Color.parseColor("#7D848D"))
                binding.tvNearMe.setBackgroundResource(R.drawable.custom_bg_f7f7f9_round_8)
                isClickNearMe = false

                workerRequestAdapter.setListWorker(listWorker)
            }
        }

        binding.tvFeedback.setOnClickListener {
            binding.tvPrice.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down, 0)

            if (!isClickFeedback) {
                binding.tvFeedback.setBackgroundResource(R.drawable.custom_bg_ffa92e_round_8)
                isClickFeedback = true
                binding.tvFeedback.setTextColor(Color.parseColor("#FFFFFF"))

                binding.tvNearMe.setBackgroundResource(R.drawable.custom_bg_f7f7f9_round_8)
                binding.tvPublic.setBackgroundResource(R.drawable.custom_bg_f7f7f9_round_8)
                binding.tvPrice.setBackgroundResource(R.drawable.custom_bg_f7f7f9_round_8)
                binding.tvNearMe.setTextColor(Color.parseColor("#7D848D"))
                binding.tvPublic.setTextColor(Color.parseColor("#7D848D"))
                binding.tvPrice.setTextColor(Color.parseColor("#7D848D"))
                isClickNearMe = false
                isClickPublic = false
                isClickPrice = false

                if (listWorker.size > 0) {
                    val listChange: MutableList<WorkerInfo> = ArrayList()
                    listChange.add(listWorker[2])
                    listChange.add(listWorker[0])
                    listChange.add(listWorker[1])
                    listChange.add(listWorker[3])

                    workerRequestAdapter.setListWorker(listChange)
                }

            } else {
                binding.tvFeedback.setTextColor(Color.parseColor("#7D848D"))
                binding.tvFeedback.setBackgroundResource(R.drawable.custom_bg_f7f7f9_round_8)
                isClickFeedback = false

                workerRequestAdapter.setListWorker(listWorker)
            }
        }

        binding.tvPublic.setOnClickListener {
            binding.tvPrice.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down, 0)

            if (!isClickPublic) {
                binding.tvPublic.setBackgroundResource(R.drawable.custom_bg_ffa92e_round_8)
                isClickPublic = true
                binding.tvPublic.setTextColor(Color.parseColor("#FFFFFF"))

                binding.tvNearMe.setBackgroundResource(R.drawable.custom_bg_f7f7f9_round_8)
                binding.tvFeedback.setBackgroundResource(R.drawable.custom_bg_f7f7f9_round_8)
                binding.tvPrice.setBackgroundResource(R.drawable.custom_bg_f7f7f9_round_8)
                binding.tvNearMe.setTextColor(Color.parseColor("#7D848D"))
                binding.tvFeedback.setTextColor(Color.parseColor("#7D848D"))
                binding.tvPrice.setTextColor(Color.parseColor("#7D848D"))
                isClickNearMe = false
                isClickFeedback = false
                isClickPrice = false

                if (listWorker.size > 0) {
                    val listChange: MutableList<WorkerInfo> = ArrayList()
                    listChange.add(listWorker[3])
                    listChange.add(listWorker[0])
                    listChange.add(listWorker[1])
                    listChange.add(listWorker[2])

                    workerRequestAdapter.setListWorker(listChange)
                }

            } else {
                binding.tvPublic.setTextColor(Color.parseColor("#7D848D"))
                binding.tvPublic.setBackgroundResource(R.drawable.custom_bg_f7f7f9_round_8)
                isClickPublic = false

                workerRequestAdapter.setListWorker(listWorker)
            }
        }

        binding.tvPrice.setOnClickListener {
            if (!isClickPrice) {
                binding.tvPrice.setBackgroundResource(R.drawable.custom_bg_ffa92e_round_8)
                isClickPrice = true
                binding.tvPrice.setTextColor(Color.parseColor("#FFFFFF"))

                binding.tvNearMe.setBackgroundResource(R.drawable.custom_bg_f7f7f9_round_8)
                binding.tvFeedback.setBackgroundResource(R.drawable.custom_bg_f7f7f9_round_8)
                binding.tvPublic.setBackgroundResource(R.drawable.custom_bg_f7f7f9_round_8)
                binding.tvNearMe.setTextColor(Color.parseColor("#7D848D"))
                binding.tvFeedback.setTextColor(Color.parseColor("#7D848D"))
                binding.tvPublic.setTextColor(Color.parseColor("#7D848D"))
                binding.tvPrice.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down_white, 0)
                isClickNearMe = false
                isClickFeedback = false
                isClickPublic = false

                if (listWorker.size > 0) {
                    val listChange: MutableList<WorkerInfo> = ArrayList()
                    listChange.add(listWorker[3])
                    listChange.add(listWorker[0])
                    listChange.add(listWorker[1])
                    listChange.add(listWorker[2])

                    workerRequestAdapter.setListWorker(listChange)
                }

            } else {
                binding.tvPrice.setTextColor(Color.parseColor("#7D848D"))
                binding.tvPrice.setBackgroundResource(R.drawable.custom_bg_f7f7f9_round_8)
                binding.tvPrice.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down, 0)

                isClickPrice = false

                workerRequestAdapter.setListWorker(listWorker)
            }
        }
    }

    override fun onPermissionGranted() {

    }

    override fun observerData() {
        mainViewModel.listWorkerLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                listWorker.clear()
                listWorker.addAll(it)
                workerRequestAdapter.setListWorker(it)
            }
        }
    }

    override fun onRequestClick(id: Int) {

    }

    override fun onInfoClick(id: Int) {
        navigateScreen(null, R.id.workerInfoFragment)
    }
}