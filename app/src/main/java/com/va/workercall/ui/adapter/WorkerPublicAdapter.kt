package com.va.workercall.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.va.workercall.R
import com.va.workercall.common.models.WorkerInfo
import com.va.workercall.databinding.ItemPublicBinding

class WorkerPublicAdapter(private val mContext: Context, private val workerPublicListener: WorkerPublicListener) :
    RecyclerView.Adapter<WorkerPublicAdapter.WorkerPublicViewHolder>() {
    private var listWorker: MutableList<WorkerInfo> = ArrayList()


    class WorkerPublicViewHolder(var binding: ItemPublicBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    interface WorkerPublicListener {
        fun itemClick(id: Int)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setListWorker(list: MutableList<WorkerInfo>) {
        this.listWorker = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkerPublicViewHolder {
        val binding: ItemPublicBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_public, parent, false)
        return WorkerPublicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WorkerPublicViewHolder, position: Int) {
        val worker: WorkerInfo = listWorker[position]
        holder.binding.tvName.text = worker.name
        holder.binding.ivPerson.setBackgroundResource(R.drawable.ic_image_person_1)
        holder.binding.tvPrice.text = worker.price.toString()
        holder.binding.tvRate.text = worker.rate.toString()
        holder.binding.tvPlace.text = worker.place

        val layoutManager: LinearLayoutManager = LinearLayoutManager(mContext)
        layoutManager.orientation = RecyclerView.HORIZONTAL
        holder.binding.rvWorkerTips.layoutManager = layoutManager

        val workerTipAdapter: WorkerTipAdapter = WorkerTipAdapter()
        workerTipAdapter.setListWorkerTip(worker.listTip)

        holder.binding.rvWorkerTips.adapter = workerTipAdapter

        holder.itemView.setOnClickListener {
            workerPublicListener.itemClick(worker.id)
        }
    }

    override fun getItemCount(): Int {
        return if (listWorker.isNotEmpty()) {
            listWorker.size
        } else 0
    }
}