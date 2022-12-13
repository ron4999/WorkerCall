package com.va.workercall.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.va.workercall.R
import com.va.workercall.databinding.DialogBottomCancelReceiptBinding

class DialogBottomCancelReceipt constructor(var onCancelReceipt: CancelReceiptListener) : BottomSheetDialogFragment() {
    lateinit var binding: DialogBottomCancelReceiptBinding
    private var reason1: Boolean = false
    private var reason2: Boolean = false
    private var reason3: Boolean = false
    private var reason4: Boolean = false
    private var reason5: Boolean = false
    private var isWorker = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_bottom_cancel_receipt, container, false)
        return binding.root
    }

    fun setWorker() {
        isWorker = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()

        binding.tvReason1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_not_choose, 0)
        binding.tvReason2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_not_choose, 0)
        binding.tvReason3.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_not_choose, 0)
        binding.tvReason4.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_not_choose, 0)
        binding.tvReason5.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_not_choose, 0)

//        binding.tvReason1.setCompoundDrawables(null, null, requireContext().getDrawable(R.drawable.ic_not_choose), null)
//        binding.tvReason2.setCompoundDrawables(null, null, requireContext().getDrawable(R.drawable.ic_not_choose), null)
//        binding.tvReason3.setCompoundDrawables(null, null, requireContext().getDrawable(R.drawable.ic_not_choose), null)
//        binding.tvReason4.setCompoundDrawables(null, null, requireContext().getDrawable(R.drawable.ic_not_choose), null)
//        binding.tvReason5.setCompoundDrawables(null, null, requireContext().getDrawable(R.drawable.ic_not_choose), null)

        onClickListener()

        initListener()
    }

    private fun initData() {
        if (isWorker) {
            binding.tvReason1.setText("Khách ở quá xa")
            binding.tvReason2.setText("Không sửa được")
            binding.tvReason3.setText("Có việc đột xuất")
            binding.tvReason4.setText("Không liên hệ được với khách")
        }
    }

    private fun onClickListener() {
        binding.tvReason1.setOnClickListener {
            if (!reason1) {
                binding.tvReason1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_choose, 0)
                binding.tvReason2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_not_choose, 0)
                binding.tvReason3.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_not_choose, 0)
                binding.tvReason4.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_not_choose, 0)
                binding.tvReason5.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_not_choose, 0)
                reason1 = true
            } else {
                binding.tvReason1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_not_choose, 0)
                reason1 = false
            }
        }

        binding.tvReason2.setOnClickListener {
            if (!reason2) {
                binding.tvReason2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_choose, 0)
                binding.tvReason1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_not_choose, 0)
                binding.tvReason3.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_not_choose, 0)
                binding.tvReason4.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_not_choose, 0)
                binding.tvReason5.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_not_choose, 0)
                reason2 = true
            } else {
                binding.tvReason2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_not_choose, 0)
                reason2 = false
            }
        }

        binding.tvReason3.setOnClickListener {
            if (!reason3) {
                binding.tvReason3.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_choose, 0)
                binding.tvReason1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_not_choose, 0)
                binding.tvReason2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_not_choose, 0)
                binding.tvReason4.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_not_choose, 0)
                binding.tvReason5.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_not_choose, 0)
                reason3 = true
            } else {
                binding.tvReason3.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_not_choose, 0)
                reason3 = false
            }
        }

        binding.tvReason4.setOnClickListener {
            if (!reason4) {
                binding.tvReason4.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_choose, 0)
                binding.tvReason1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_not_choose, 0)
                binding.tvReason3.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_not_choose, 0)
                binding.tvReason2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_not_choose, 0)
                binding.tvReason5.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_not_choose, 0)
                reason4 = true
            } else {
                binding.tvReason4.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_not_choose, 0)
                reason4 = false
            }
        }

        binding.tvReason5.setOnClickListener {
            if (!reason5) {
                binding.tvReason5.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_choose, 0)
                binding.tvReason1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_not_choose, 0)
                binding.tvReason3.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_not_choose, 0)
                binding.tvReason4.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_not_choose, 0)
                binding.tvReason2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_not_choose, 0)
                reason5 = true
            } else {
                binding.tvReason5.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_not_choose, 0)
                reason5 = false
            }
        }
    }

    interface CancelReceiptListener {
        fun onConfirmClick()
    }

    private fun initListener() {
        binding.tvCancel.setOnClickListener {
            dismiss()
        }

        binding.tvConfirm.setOnClickListener {
            dismiss()
            onCancelReceipt.onConfirmClick()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return dialog
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.MaterialDialogSheet)
    }
}