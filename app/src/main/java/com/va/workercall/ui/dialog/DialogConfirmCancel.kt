package com.va.workercall.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.va.workercall.R
import com.va.workercall.databinding.DialogConfirmCancelBinding
import com.va.workercall.ui.base.BaseBindingDialogFragment

class DialogConfirmCancel constructor(var onClickListener: OnClickListener) : BaseBindingDialogFragment<DialogConfirmCancelBinding>() {
    interface OnClickListener {
        fun onClickLater()

        fun onClickMessage()
    }

    override val layoutId: Int
        get() = R.layout.dialog_confirm_cancel

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        binding.tvGoHome.setOnClickListener {
            dismiss()
            onClickListener.onClickLater()
        }

        binding.tvDetail.setOnClickListener {
            dismiss()
            onClickListener.onClickMessage()
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