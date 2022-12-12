package com.va.workercall.ui.main.register

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.core.widget.doOnTextChanged
import com.va.workercall.R
import com.va.workercall.databinding.FragmentRegisterBinding
import com.va.workercall.ui.base.BaseBindingFragment
import com.va.workercall.ui.main.MainViewModel

class RegisterFragment: BaseBindingFragment<FragmentRegisterBinding, MainViewModel>() {
    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_register

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        onSetTextHaveAccount()

        onTextChangeListener()
    }

    private fun onTextChangeListener() {
        binding.edtRegUsername.doOnTextChanged { text, start, before, count ->
            if (text != null) {
                if (text.isNotEmpty()) {
                    if (binding.edtRegPassword.text.isNotEmpty()) {
                        binding.btnRegNone.visibility = View.INVISIBLE
                        binding.btnReg.visibility = View.VISIBLE
                    }
                } else {
                    binding.btnRegNone.visibility = View.VISIBLE
                    binding.btnReg.visibility = View.INVISIBLE
                }
            }
        }

        binding.edtRegPassword.doOnTextChanged { text, start, before, count ->
            if (text != null) {
                if (text.isNotEmpty()) {
                    if (binding.edtRegUsername.text.isNotEmpty()) {
                        binding.btnRegNone.visibility = View.INVISIBLE
                        binding.btnReg.visibility = View.VISIBLE
                    }
                } else {
                    binding.btnRegNone.visibility = View.VISIBLE
                    binding.btnReg.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun onSetTextHaveAccount() {
        val strConditionLink: String = "Bạn đã có tài khoản? Đăng nhập"


        // check if text have link
        val index: MutableList<Int> = ArrayList()
        for (i in strConditionLink.indices) {
            val current = strConditionLink[i]
            if (current == '?') {
                index.add(i)
            }
        }

        val spannableString: SpannableString = SpannableString(strConditionLink)
        val registerClick: ClickableSpan = getClickableSpan()
        spannableString.setSpan(registerClick, index[0] + 2, strConditionLink.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.tvHaveAccount.text = spannableString
        binding.tvHaveAccount.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun getClickableSpan(): ClickableSpan {
        return object : ClickableSpan() {
            override fun onClick(widget: View) {
                popBackStack()
                navigateScreen(null, R.id.loginFragment)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.parseColor("#FFA92E")
                ds.isUnderlineText = false
            }
        }
    }

    override fun onPermissionGranted() {
    }

    override fun observerData() {
    }
}