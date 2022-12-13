package com.va.workercall.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.va.workercall.R
import com.va.workercall.databinding.DialogFinishPaymentBinding
import com.va.workercall.ui.base.BaseBindingDialogFragment

class DialogFinishPayment constructor(var onClickListener: OnClickListener) : BaseBindingDialogFragment<DialogFinishPaymentBinding>() {
    interface OnClickListener {
        fun onClickGoHome()

        fun onClickDetail()
    }

    override val layoutId: Int
        get() = R.layout.dialog_finish_payment

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        binding.tvGoHome.setOnClickListener {
            dismiss()
            onClickListener.onClickGoHome()
        }

        binding.tvDetail.setOnClickListener {
            dismiss()
            onClickListener.onClickDetail()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setLayout(
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