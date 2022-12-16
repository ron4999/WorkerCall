package com.va.workercall.ui.main.register

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import com.va.workercall.ui.main.MainActivity
import com.va.workercall.ui.main.MainViewModel
import androidx.activity.OnBackPressedCallback
import com.va.workercall.utils.hideKeyboard

class RegisterFragment: BaseBindingFragment<FragmentRegisterBinding, MainViewModel>() {
    private var showConfirmCode = false
    private var isCreateUser = false
    private var registerUser = false

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_register

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        arguments?.let {
            registerUser = it.getBoolean("REGISTER_WORKER")
            it.remove("REGISTER_WORKER")
        }
        if (registerUser) {
            binding.layoutCreateUser.root.visibility = View.VISIBLE
        }
        onSetTextHaveAccount()

        onTextChangeListener()

        onClickListener()

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            mBackPressedCallback
        )
    }


    private fun onClickListener() {
        binding.btnReg.setOnClickListener{
            requireView().hideKeyboard()
            if (MainActivity.isWorker) {
                showConfirmCode = true
                binding.layoutConfirmCode.root.visibility = View.VISIBLE
                Handler(Looper.getMainLooper()).postDelayed(Runnable {
                    if (isAdded) {
                        binding.layoutConfirmCode.ivCode.setImageResource(R.drawable.img_confirm_code_2)
                        binding.layoutConfirmCode.btnContinue.setBackgroundResource(R.drawable.custom_bg_ffa92e_round_16)
                        binding.layoutConfirmCode.btnContinue.isEnabled = true
                        binding.layoutConfirmCode.btnContinue.isClickable = true
                    }
                }, 3000)

            }
        }
        binding.layoutConfirmCode.btnContinue.setOnClickListener{
            requireView().hideKeyboard()
//            goneConfirmCode()
//            isCreateUser = true
//            binding.layoutCreateUser.root.visibility = View.VISIBLE

            navigateScreen(null, R.id.fragmentTakePhoto)
        }
        binding.layoutConfirmCode.ivBack.setOnClickListener{
            goneConfirmCode()
        }

        binding.layoutCreateUser.btnFinish.setOnClickListener {
            navigateScreen(null, R.id.loginFragment)
        }
    }

    private fun goneConfirmCode() {
        showConfirmCode = false
        binding.layoutConfirmCode.ivCode.setImageResource(R.drawable.img_confirm_code_1)
        binding.layoutConfirmCode.root.visibility = View.GONE
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

        binding.layoutCreateUser.etPass.doOnTextChanged { text, start, before, count ->
            if (text != null) {
                if (text.isNotEmpty()) {
                    if (binding.layoutCreateUser.etPass2.text.isNotEmpty()) {
                        enableBtnCreate(true)
                    } else {
                        enableBtnCreate(false)
                    }
                } else {
                    enableBtnCreate(false)
                }
            }
        }

        binding.layoutCreateUser.etPass2.doOnTextChanged { text, start, before, count ->
            if (text != null) {
                if (text.isNotEmpty()) {
                    if (binding.layoutCreateUser.etPass.text.isNotEmpty()) {
                       enableBtnCreate(true)
                    } else {
                        enableBtnCreate(false)
                    }
                } else {
                    enableBtnCreate(false)
                }
            }
        }
    }

    private fun enableBtnCreate(isEnable : Boolean) {
        if (isEnable) {
            binding.layoutCreateUser.btnFinish.setBackgroundResource(R.drawable.custom_bg_ffa92e_round_16)
            binding.layoutCreateUser.btnFinish.isClickable = true
            binding.layoutCreateUser.btnFinish.isEnabled = true
        } else {
            binding.layoutCreateUser.btnFinish.setBackgroundResource(R.drawable.custom_bg_cecece)
            binding.layoutCreateUser.btnFinish.isClickable = false
            binding.layoutCreateUser.btnFinish.isEnabled = false
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

    private val mBackPressedCallback: OnBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                backPress()
            }
        }

    private fun backPress() {
        if (showConfirmCode) {
            goneConfirmCode()
        } else if (isCreateUser) {
            isCreateUser = false
            binding.layoutCreateUser.root.visibility = View.GONE
        } else {
            mBackPressedCallback.isEnabled = false
            requireActivity().onBackPressed()
        }
    }

    override fun onPermissionGranted() {
    }

    override fun observerData() {
    }
}