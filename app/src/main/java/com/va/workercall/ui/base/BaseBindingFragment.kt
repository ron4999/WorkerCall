package com.va.workercall.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.va.workercall.ui.main.MainViewModel

abstract class BaseBindingFragment<B : ViewDataBinding, T : BaseViewModel> :
    BaseFragment() {
    lateinit var binding: B
    lateinit var viewModel: T
    lateinit var mainViewModel: MainViewModel
    private var loaded = false
    protected abstract fun getViewModel(): Class<T>
    abstract val layoutId: Int

    protected abstract fun onCreatedView(view: View?, savedInstanceState: Bundle?)
    protected abstract fun onPermissionGranted()
    protected abstract fun observerData()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(getViewModel())
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        if (!needToKeepView()) {
            onCreatedView(view, savedInstanceState)
        } else {
            if (!loaded) {
                onCreatedView(view, savedInstanceState)
                loaded = true
            }
        }
        observerData()
    }

    open fun needToKeepView(): Boolean {
        return false
    }

    fun popBackStack() {
        findNavController().popBackStack()
    }

    fun popBackStackWithInclusive(id: Int, inclusive: Boolean) {
        findNavController().popBackStack(id, inclusive)
    }

    fun navigateScreenWithInclusive(bundle: Bundle?, id: Int, inclusive: Boolean) {
        val navBuilder = NavOptions.Builder()

        findNavController().navigate(id, bundle, navBuilder.build())
    }

    fun navigateScreen(bundle: Bundle?, id: Int) {
        val navBuilder = NavOptions.Builder()
//        navBuilder.setEnterAnim(R.anim.fade_in)
//            .setExitAnim(R.anim.fade_out)
//            .setPopEnterAnim(R.anim.fade_in)
//            .setPopExitAnim(R.anim.slide_out_top)

        findNavController().navigate(id, bundle, navBuilder.build())

//            (requireActivity() as MainActivity).mNavController?.navigate(
//                id,
//                bundle,
//                navBuilder.build()
//            )
    }
}