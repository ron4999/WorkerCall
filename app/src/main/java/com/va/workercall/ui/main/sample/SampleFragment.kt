package com.va.workercall.ui.main.sample

import com.va.workercall.databinding.FragmentSampleBinding


// start copy
import android.os.Bundle
import android.view.View
import com.va.workercall.R
import com.va.workercall.ui.base.BaseBindingFragment
import com.va.workercall.ui.main.MainViewModel

class SampleFragment : BaseBindingFragment<FragmentSampleBinding, MainViewModel>() {
    //tên Fragment đã đặt//       //Tên layout viết liền thêm ...Binding//

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_sample // thay bang file layout: (VD: R.layout.(tên))

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        onClickListener()
    }

    private fun onClickListener() {
        /*
        binding.(id - view cần ấn).setOnClickListener {
            // chuyển sang màn sau
            navigate(null, R.id.(id của màn trong main_nav.xml))

            // back về màn trước
            popBackStack()

            -> chọn một trong 2
        }
         */

    }

    override fun onPermissionGranted() {

    }

    override fun observerData() {

    }
}

// end copy