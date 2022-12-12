package com.va.workercall.ui.main.addpayment

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import com.va.workercall.R
import com.va.workercall.data.local.SharedPreferenceHelper
import com.va.workercall.databinding.FragmentAddPaymentBinding
import com.va.workercall.ui.base.BaseBindingFragment
import com.va.workercall.ui.dialog.DialogAddPaymentSuccess
import com.va.workercall.ui.main.MainViewModel

class AddPaymentFragment : BaseBindingFragment<FragmentAddPaymentBinding, MainViewModel>(), DialogAddPaymentSuccess.OnClickListener {
    private var isDefault = false

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_add_payment

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        onClickListener()

        setEditText()

        setDefault()
    }

    private fun setDefault() {
        binding.tvSetDefault.setOnClickListener {
            if (!isDefault) {
//            binding.tvReason1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_not_choose, 0)

                binding.tvSetDefault.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_choose, 0, 0, 0)
                isDefault = true
            } else {
                binding.tvSetDefault.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_not_choose, 0, 0, 0)
                isDefault = false
            }
        }
    }

    private fun setEditText() {
        binding.edtCardNumber.doOnTextChanged { text, start, before, count ->
            if (text != null) {
                if (text.isNotEmpty()) {
                    if (binding.edtCvv.text.isNotEmpty() && binding.edtId.text.isNotEmpty() &&
                            binding.edtOwner.text.isNotEmpty() && binding.edtDateExpired.text.isNotEmpty()) {
                        binding.tvAddCardNone.visibility = View.INVISIBLE
                        binding.tvAddCard.visibility = View.VISIBLE
                    }
                } else {
                    binding.tvAddCardNone.visibility = View.VISIBLE
                    binding.tvAddCard.visibility = View.GONE
                }
            }
        }

        binding.edtCvv.doOnTextChanged { text, start, before, count ->
            if (text != null) {
                if (text.isNotEmpty()) {
                    if (binding.edtCardNumber.text.isNotEmpty() && binding.edtId.text.isNotEmpty() &&
                        binding.edtOwner.text.isNotEmpty() && binding.edtDateExpired.text.isNotEmpty()) {
                        binding.tvAddCardNone.visibility = View.INVISIBLE
                        binding.tvAddCard.visibility = View.VISIBLE
                    }
                } else {
                    binding.tvAddCardNone.visibility = View.VISIBLE
                    binding.tvAddCard.visibility = View.GONE
                }
            }
        }

        binding.edtId.doOnTextChanged { text, start, before, count ->
            if (text != null) {
                if (text.isNotEmpty()) {
                    if (binding.edtCardNumber.text.isNotEmpty() && binding.edtCvv.text.isNotEmpty() &&
                        binding.edtOwner.text.isNotEmpty() && binding.edtDateExpired.text.isNotEmpty()) {
                        binding.tvAddCardNone.visibility = View.INVISIBLE
                        binding.tvAddCard.visibility = View.VISIBLE
                    }
                } else {
                    binding.tvAddCardNone.visibility = View.VISIBLE
                    binding.tvAddCard.visibility = View.GONE
                }
            }
        }

        binding.edtOwner.doOnTextChanged { text, start, before, count ->
            if (text != null) {
                if (text.isNotEmpty()) {
                    if (binding.edtCardNumber.text.isNotEmpty() && binding.edtCvv.text.isNotEmpty() &&
                        binding.edtId.text.isNotEmpty() && binding.edtDateExpired.text.isNotEmpty()) {
                        binding.tvAddCardNone.visibility = View.INVISIBLE
                        binding.tvAddCard.visibility = View.VISIBLE
                    }
                } else {
                    binding.tvAddCardNone.visibility = View.VISIBLE
                    binding.tvAddCard.visibility = View.GONE
                }
            }
        }

        binding.edtDateExpired.doOnTextChanged { text, start, before, count ->
            if (text != null) {
                if (text.isNotEmpty()) {
                    if (binding.edtCardNumber.text.isNotEmpty() && binding.edtCvv.text.isNotEmpty() &&
                        binding.edtId.text.isNotEmpty() && binding.edtOwner.text.isNotEmpty()) {
                        binding.tvAddCardNone.visibility = View.INVISIBLE
                        binding.tvAddCard.visibility = View.VISIBLE
                    }
                } else {
                    binding.tvAddCardNone.visibility = View.VISIBLE
                    binding.tvAddCard.visibility = View.GONE
                }
            }
        }
    }

    private fun onClickListener() {
        binding.ivBack.setOnClickListener {
            popBackStack()
        }

        binding.tvAddCard.setOnClickListener {
            val dialogAddPaymentSuccess: DialogAddPaymentSuccess = DialogAddPaymentSuccess(this)
            dialogAddPaymentSuccess.show(childFragmentManager, null)
        }
    }

    override fun onPermissionGranted() {

    }

    override fun observerData() {

    }

    override fun onClickBackToPayment() {
        SharedPreferenceHelper.storeBoolean("IS_ADD", true)
        popBackStack()
    }
}