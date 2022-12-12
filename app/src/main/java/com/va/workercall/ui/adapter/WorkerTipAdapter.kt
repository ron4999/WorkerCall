package com.va.workercall.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.va.workercall.R
import com.va.workercall.databinding.ItemWorkerTipBinding

class WorkerTipAdapter() : RecyclerView.Adapter<WorkerTipAdapter.WorkerTipViewHolder>() {
    private var listWorkerTip: List<String> = ArrayList()

    class WorkerTipViewHolder(var binding: ItemWorkerTipBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setListWorkerTip(list: List<String>) {
        this.listWorkerTip = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkerTipViewHolder {
        val binding: ItemWorkerTipBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_worker_tip, parent, false)
        return WorkerTipViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WorkerTipViewHolder, position: Int) {
        val tip: String = listWorkerTip[position]
        holder.binding.tvTip.text = tip
    }

    override fun getItemCount(): Int {
        return if (listWorkerTip.isNotEmpty()) {
            listWorkerTip.size
        } else 0
    }
}