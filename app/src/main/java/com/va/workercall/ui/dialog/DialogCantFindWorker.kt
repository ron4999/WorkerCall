package com.va.workercall.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.va.workercall.R
import com.va.workercall.databinding.DialogCantFindWorkerBinding
import com.va.workercall.ui.base.BaseBindingDialogFragment

class DialogCantFindWorker constructor(var onClickListener: OnClickListener) : BaseBindingDialogFragment<DialogCantFindWorkerBinding>() {
    override val layoutId: Int
        get() = R.layout.dialog_cant_find_worker

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        binding.tvClose.setOnClickListener {
            onClickListener.onClickClose()
        }

        binding.tvEditRequest.setOnClickListener {
            onClickListener.onClickEdit()
        }
    }

    interface OnClickListener {
        fun onClickClose()

        fun onClickEdit()
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