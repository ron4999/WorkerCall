package com.va.workercall.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.va.workercall.R
import com.va.workercall.common.models.WorkerInfo
import com.va.workercall.databinding.ItemWorkerWithRequestBinding

class WorkerWithRequestAdapter(private val mContext: Context, private val workerRequestListener: WorkerRequestListener) :
    RecyclerView.Adapter<WorkerWithRequestAdapter.WorkerRequestViewHolder>() {
    private var listWorker: MutableList<WorkerInfo> = ArrayList()

    class WorkerRequestViewHolder(var binding: ItemWorkerWithRequestBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    interface WorkerRequestListener {
        fun onRequestClick(id: Int)

        fun onInfoClick(id: Int)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setListWorker(list: MutableList<WorkerInfo>) {
        this.listWorker = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkerRequestViewHolder {
        val binding: ItemWorkerWithRequestBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_worker_with_request, parent, false)
        return WorkerRequestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WorkerRequestViewHolder, position: Int) {
        val worker: WorkerInfo = listWorker[position]
        holder.binding.tvName.text = worker.name
        holder.binding.tvPrice.text = "${worker.price}vnđ"
        holder.binding.tvPlace.text = worker.place
        holder.binding.tvJob.text = worker.description

        holder.binding.tvSendRequest.setOnClickListener {
            workerRequestListener.onRequestClick(worker.id)
        }

        holder.binding.tvInfo.setOnClickListener {
            workerRequestListener.onInfoClick(worker.id)
        }
    }

    override fun getItemCount(): Int {
        return if (listWorker.isNotEmpty()) {
            listWorker.size
        } else 0
    }
}