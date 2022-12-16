package com.va.workercall.ui.main.login

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import com.va.workercall.R
import com.va.workercall.databinding.FragmentLoginBinding
import com.va.workercall.ui.base.BaseBindingFragment
import com.va.workercall.ui.main.MainActivity
import com.va.workercall.ui.main.MainViewModel

class LoginFragment : BaseBindingFragment<FragmentLoginBinding, MainViewModel>() {
    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_login

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        onSetTextDontHaveAccount()


//        if (!binding.edtLoginUsername.equals("") && binding.edtLoginPassword.equals("")) {
//            binding.btnLoginNone.background = ContextCompat.getDrawable(requireContext(), R.drawable.custom_bg_gradient)
//        } else {
//            binding.btnLoginNone.background = ContextCompat.getDrawable(requireContext(), R.drawable.custom_bg_cecece)
//        }

        onTextChangeListener()

        onClickListener()

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            mBackPressedCallback
        )
    }

    private val mBackPressedCallback: OnBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        }

    private fun onTextChangeListener() {
        binding.edtLoginUsername.doOnTextChanged { text, start, before, count ->
            if (text != null) {
                if (text.isNotEmpty()) {
                    if (binding.edtLoginPassword.text.isNotEmpty()) {
                        binding.btnLoginNone.visibility = View.INVISIBLE
                        binding.btnLogin.visibility = View.VISIBLE
                    }
                } else {
                    binding.btnLoginNone.visibility = View.VISIBLE
                    binding.btnLogin.visibility = View.GONE
                }
            }
        }

        binding.edtLoginPassword.doOnTextChanged { text, start, before, count ->
            if (text != null) {
                if (text.isNotEmpty()) {
                    if (binding.edtLoginUsername.text.isNotEmpty()) {
                        binding.btnLoginNone.visibility = View.INVISIBLE
                        binding.btnLogin.visibility = View.VISIBLE
                    }
                } else {
                    binding.btnLoginNone.visibility = View.VISIBLE
                    binding.btnLogin.visibility = View.GONE
                }
            }
        }
    }

    private fun onClickListener() {
        binding.btnLogin.setOnClickListener {
            if (MainActivity.isWorker) {
                navigateScreen(null, R.id.homeWorkerFragment)
            } else {
                navigateScreen(null, R.id.homeFragment)
            }
        }
        binding.ivWorker.setOnClickListener {
            MainActivity.isWorker = true
            navigateScreen(null, R.id.registerFragment)
        }
    }

    private fun onSetTextDontHaveAccount() {
        val strConditionLink: String = "Bạn chưa có tài khoản? Đăng kí"


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

        binding.tvNotAccount.text = spannableString
        binding.tvNotAccount.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun getClickableSpan(): ClickableSpan {
        return object : ClickableSpan() {
            override fun onClick(widget: View) {
                popBackStack()
                navigateScreen(null, R.id.registerFragment)
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