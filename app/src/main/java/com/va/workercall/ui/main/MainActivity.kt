package com.va.workercall.ui.main

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import com.va.workercall.R
import com.va.workercall.databinding.ActivityMainBinding
import com.va.workercall.ui.base.BaseBindingActivity
import org.greenrobot.eventbus.EventBus

class MainActivity : BaseBindingActivity<ActivityMainBinding, MainViewModel>() {
    var mNavController: NavController? = null
    private var graph: NavGraph? = null

    companion object {
        var isWorker = false
        var isAccept = false
        var isToLocation = false
        var isWorking = false
    }

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        EventBus.getDefault().register(this)

    }

    override fun setupView(savedInstanceState: Bundle?) {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        mNavController = navHostFragment.navController
    }

    override fun setupData() {

    }

    override fun onDestroy() {
        super.onDestroy()
//        EventBus.getDefault().unregister(this)
    }


}