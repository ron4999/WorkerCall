package com.va.workercall.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.databinding.DataBindingUtil
//import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.va.workercall.R
import com.va.workercall.databinding.DialogBottomChooseTimeBinding


class DialogBottomChooseTime constructor(var chooseTimeListener: ChooseTimeListener) : BottomSheetDialogFragment() {
    lateinit var binding: DialogBottomChooseTimeBinding
    private var dateChoose = ""

    interface ChooseTimeListener {
        fun onConfirmTime(time: String)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_bottom_choose_time, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pickerStartDay.setIsAmPm(false)

        onTimeChangeListener()
    }

    private fun onTimeChangeListener() {
//        binding.chooseTimeView.addOnDateChangedListener { displayed, date ->
//
//        }
        binding.pickerStartDay.setListener { displayed, date ->
            binding.tvTimeShow.text = displayed
            dateChoose = displayed
        }

        binding.tvSetTime.setOnClickListener {
            dismiss()
            chooseTimeListener.onConfirmTime(dateChoose)
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