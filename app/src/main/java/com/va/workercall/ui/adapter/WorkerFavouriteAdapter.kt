package com.va.workercall.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.va.workercall.R
import com.va.workercall.common.models.WorkerInfo
import com.va.workercall.databinding.ItemWorkerFavouriteBinding

class WorkerFavouriteAdapter(private val mContext: Context, private val workerFavListener: WorkerFavListener) :
    RecyclerView.Adapter<WorkerFavouriteAdapter.WorkerFavViewHolder>() {
    private var listWorker: MutableList<WorkerInfo> = ArrayList()

    interface WorkerFavListener {
        fun onRequestClick(id: Int)

        fun onInfoClick(id: Int)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setListWorker(list: MutableList<WorkerInfo>) {
        this.listWorker = list
        notifyDataSetChanged()
    }

    class WorkerFavViewHolder(var binding: ItemWorkerFavouriteBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkerFavViewHolder {
        val binding: ItemWorkerFavouriteBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_worker_favourite, parent, false)
        return WorkerFavViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WorkerFavViewHolder, position: Int) {
        val worker: WorkerInfo = listWorker[position]
        holder.binding.tvName.text = worker.name
        holder.binding.tvPrice.text = "${worker.price}vnđ"
        holder.binding.tvPlace.text = worker.place
        holder.binding.tvRate.text = worker.rate.toString()
        holder.binding.tvWorkerJob.text = worker.listTip[0]

        holder.binding.tvSendRequest.setOnClickListener {
            workerFavListener.onRequestClick(worker.id)
        }

        holder.binding.tvInfo.setOnClickListener {
            workerFavListener.onInfoClick(worker.id)
        }
    }

    override fun getItemCount(): Int {
        return if (listWorker.isNotEmpty()) {
            listWorker.size
        } else 0
    }
}