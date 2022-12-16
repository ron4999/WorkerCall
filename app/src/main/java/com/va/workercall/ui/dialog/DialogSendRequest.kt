package com.va.workercall.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.va.workercall.R
import com.va.workercall.databinding.DialogSendRequestBinding
import com.va.workercall.ui.base.BaseBindingDialogFragment
import com.va.workercall.ui.main.MainActivity

class DialogSendRequest constructor(var onClickListener: OnClickListener) : BaseBindingDialogFragment<DialogSendRequestBinding>() {
    var isFinish = false

    @JvmName("setFinish1")
    fun setFinish(boolean: Boolean) {
        isFinish = boolean
    }

    interface OnClickListener {
        fun onClickLater()

        fun onClickMessage()
    }

    override val layoutId: Int
        get() = R.layout.dialog_send_request

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        if (MainActivity.isWorker) {
            binding.tvPleaseWait.setText("Bạn đã đồng ý thực hiện")
            binding.tvWaitingTip.visibility = View.GONE
        }

        if (isFinish) {
            binding.tvPleaseWait.setText("Hoàn thành công việc")
            binding.tvLater.setText("Về trang chủ")
            binding.tvMessage.setText("Chi tiết dịch vụ")
        }

        binding.tvLater.setOnClickListener {
            dismiss()
            onClickListener.onClickLater()
        }

        binding.tvMessage.setOnClickListener {
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