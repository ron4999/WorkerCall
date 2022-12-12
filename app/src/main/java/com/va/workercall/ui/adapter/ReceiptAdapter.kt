package com.va.workercall.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.va.workercall.R
import com.va.workercall.common.models.Receipt
import com.va.workercall.databinding.ItemPublicBinding
import com.va.workercall.databinding.ItemReceiptBinding

class ReceiptAdapter(private val mContext: Context, private val onReceiptListener: OnReceiptListener):
    RecyclerView.Adapter<ReceiptAdapter.ReceiptViewHolder>() {
    private var listReceipt: MutableList<Receipt> = ArrayList()

    class ReceiptViewHolder(var binding: ItemReceiptBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    interface OnReceiptListener {
        fun detailClick(id: Int)

        fun seeRate(id: Int)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setListReceipt(list: MutableList<Receipt>) {
        this.listReceipt = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiptViewHolder {
        val binding: ItemReceiptBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_receipt, parent, false)
        return ReceiptViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReceiptViewHolder, position: Int) {
        val receipt: Receipt = listReceipt[position]

        holder.binding.tvBookingNumber.text = "Booking No: ${receipt.id}"
        holder.binding.tvName.text = receipt.workerName
        when (receipt.status) {
            1 -> {
                holder.binding.tvStatus.text = "Đang chờ"
                holder.binding.tvSeeRate.visibility = View.GONE
                holder.binding.tvBookingNumber.setBackgroundColor(Color.parseColor("#FFA92E"))
                holder.binding.tvDetail.setBackgroundResource(R.drawable.custom_bg_ffa92e)
            }
            2 -> {
                holder.binding.tvStatus.text = "Đã xác nhận"
                holder.binding.tvSeeRate.visibility = View.GONE
                holder.binding.tvBookingNumber.setBackgroundColor(Color.parseColor("#FFA92E"))
                holder.binding.tvDetail.setBackgroundResource(R.drawable.custom_bg_ffa92e)
            }
            3 -> {
                holder.binding.tvStatus.text = "Hoàn thành"
                holder.binding.tvSeeRate.visibility = View.VISIBLE
                holder.binding.tvBookingNumber.setBackgroundColor(Color.parseColor("#FFA92E"))
                holder.binding.tvDetail.setBackgroundResource(R.drawable.custom_bg_ffa92e)
            }
            4 -> {
                holder.binding.tvStatus.text = "Đã hủy"
                holder.binding.tvSeeRate.visibility = View.GONE
                holder.binding.tvBookingNumber.setBackgroundColor(Color.parseColor("#EB4335"))
                holder.binding.tvDetail.setBackgroundResource(R.drawable.custom_bg_eb4335)
            }
        }

        holder.binding.tvBookingDate.text = receipt.time
        holder.binding.tvBookingPlace.text = receipt.place
        holder.binding.tvJob.text = receipt.workerJob

        holder.binding.tvDetail.setOnClickListener {
            onReceiptListener.detailClick(receipt.id)
        }

        holder.binding.tvSeeRate.setOnClickListener {
            onReceiptListener.seeRate(receipt.id)
        }
    }

    override fun getItemCount(): Int {
        return if (listReceipt.isNotEmpty()) {
            listReceipt.size
        } else 0
    }
}