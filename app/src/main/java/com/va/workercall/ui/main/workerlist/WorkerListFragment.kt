package com.va.workercall.ui.main.workerlist

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.va.workercall.R
import com.va.workercall.common.models.WorkerInfo
import com.va.workercall.databinding.FragmentWorkerListBinding
import com.va.workercall.ui.adapter.WorkerFavouriteAdapter
import com.va.workercall.ui.adapter.WorkerPublicAdapter
import com.va.workercall.ui.base.BaseBindingFragment
import com.va.workercall.ui.dialog.DialogSendRequest

class WorkerListFragment : BaseBindingFragment<FragmentWorkerListBinding, WorkerListViewModel>(), WorkerFavouriteAdapter.WorkerFavListener, DialogSendRequest.OnClickListener {
    private lateinit var workerFavouriteAdapter: WorkerFavouriteAdapter
    private var isClickNearMe = false
    private var isClickFeedback = false
    private var isClickPublic = false
    private var isClickPrice = false
    private var listWorker: MutableList<WorkerInfo> = ArrayList()

    override fun getViewModel(): Class<WorkerListViewModel> {
        return WorkerListViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_worker_list

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        binding.rvWorkerList.layoutManager = linearLayoutManager
        workerFavouriteAdapter = WorkerFavouriteAdapter(requireContext(), this)
        binding.rvWorkerList.adapter = workerFavouriteAdapter

        mainViewModel.getListWorkerInfo()

        onClickListener()
    }

    private fun onClickListener() {
        binding.ivBack.setOnClickListener {
            popBackStackWithInclusive(R.id.homeFragment, false)
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

                    workerFavouriteAdapter.setListWorker(listChange)
                }

            } else {
                binding.tvNearMe.setTextColor(Color.parseColor("#7D848D"))
                binding.tvNearMe.setBackgroundResource(R.drawable.custom_bg_f7f7f9_round_8)
                isClickNearMe = false

                workerFavouriteAdapter.setListWorker(listWorker)
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

                    workerFavouriteAdapter.setListWorker(listChange)
                }

            } else {
                binding.tvFeedback.setTextColor(Color.parseColor("#7D848D"))
                binding.tvFeedback.setBackgroundResource(R.drawable.custom_bg_f7f7f9_round_8)
                isClickFeedback = false

                workerFavouriteAdapter.setListWorker(listWorker)
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

                    workerFavouriteAdapter.setListWorker(listChange)
                }

            } else {
                binding.tvPublic.setTextColor(Color.parseColor("#7D848D"))
                binding.tvPublic.setBackgroundResource(R.drawable.custom_bg_f7f7f9_round_8)
                isClickPublic = false

                workerFavouriteAdapter.setListWorker(listWorker)
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

                    workerFavouriteAdapter.setListWorker(listChange)
                }

            } else {
                binding.tvPrice.setTextColor(Color.parseColor("#7D848D"))
                binding.tvPrice.setBackgroundResource(R.drawable.custom_bg_f7f7f9_round_8)
                binding.tvPrice.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down, 0)

                isClickPrice = false

                workerFavouriteAdapter.setListWorker(listWorker)
            }
        }
    }

    override fun onPermissionGranted() {

    }

    override fun observerData() {
        mainViewModel.listWorkerLiveData.observe(viewLifecycleOwner) {
            if (it != null && it.size > 0) {
                listWorker.clear()
                listWorker.addAll(it)
                workerFavouriteAdapter.setListWorker(it)
            }
        }
    }

    override fun onRequestClick(id: Int) {
        val dialogSendRequest: DialogSendRequest = DialogSendRequest(this)
        dialogSendRequest.show(childFragmentManager, null)
    }

    override fun onInfoClick(id: Int) {
        navigateScreen(null, R.id.workerInfoFragment)
    }

    override fun onClickLater() {
        popBackStackWithInclusive(R.id.homeFragment, false)
    }

    override fun onClickMessage() {
        popBackStackWithInclusive(R.id.homeFragment, false)
        navigateScreen(null, R.id.messageFragment)
    }
}