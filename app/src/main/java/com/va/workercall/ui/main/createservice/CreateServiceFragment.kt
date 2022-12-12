package com.va.workercall.ui.main.createservice

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.OnBackPressedCallback
import com.va.workercall.R
import com.va.workercall.databinding.FragmentCreateServiceBinding
import com.va.workercall.ui.base.BaseBindingFragment
import com.va.workercall.ui.dialog.DialogBottomChooseTime
import com.va.workercall.ui.dialog.DialogWaitingFindWorker
import com.va.workercall.ui.main.MainViewModel

class CreateServiceFragment : BaseBindingFragment<FragmentCreateServiceBinding, MainViewModel>(), DialogBottomChooseTime.ChooseTimeListener {
    private var isClickElectricCold = false
    private var isClickPipe = false
    private var isClickClean = false
    private var isClickElectric = false

    private var isClickOrderNow = false

    private var isShowDialog = false;

    private var isClick1 = false;
    private var isClick2 = false;
    private var isClick3 = false;
    private var isClick4 = false;
    private var isClick5 = false;
    private var isClick6 = false;

    private var textCharCount = 200
    private lateinit var findingWorkerDialog: DialogWaitingFindWorker

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_create_service

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        onClickLister()

        arguments?.let {
            val type = it.getInt("SERVICE_NO", 0)

            if (type > 0) {
                binding.tvTitleKeyWord.visibility = View.GONE
                binding.tvFix.visibility = View.GONE
                binding.tvCare.visibility = View.GONE
                binding.tvClean.visibility = View.GONE
                binding.tvInstall.visibility = View.GONE
                binding.horizontalScrollView.visibility = View.GONE
            }

            if (type == 1) {
                binding.tvTitle.text = "Bảo dưỡng điều hòa"
            } else if (type == 2) {
                binding.tvTitle.text = "Bảo dưỡng tủ lạnh"
            } else if (type == 3) {
                binding.tvTitle.text = "Tháo/lắp điều hòa"
            } else if (type == 4) {
                binding.tvTitle.text = "Bảo dưỡng máy giặt"
            } else if (type == 5) {
                binding.tvTitle.text = "Tháo điều hòa khó"
            } else {
                binding.tvTitle.text = "Tạo dịch vụ"
            }
        }

        binding.edtDescribe.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                textCharCount = 200
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                textCharCount = 200 - s.toString().length
                binding.tvMaxDescribeLength.text = "$textCharCount từ"
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        onKeyboardChangeLister()

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            mBackPressedCallback
        )
    }

    private fun onKeyboardChangeLister() {

    }

    private val mBackPressedCallback: OnBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                popBackStack()
            }
        }


    private fun onClickLister() {
        binding.ivBack.setOnClickListener { popBackStack() }

        binding.tvOrderNow.setOnClickListener {
            if (!isClickOrderNow) {
                binding.tvOrderNow.setBackgroundResource(R.drawable.custom_bg_ffa92e_round_16)
                binding.tvOrderNow.setTextColor(Color.WHITE)
                isClickOrderNow = true

                binding.tvChooseTime.text = "Chọn thời gian"
                binding.tvChooseTime.setBackgroundResource(R.drawable.custom_bg_social_round_16)
                binding.tvChooseTime.setTextColor(Color.parseColor("#494F58"))
            } else {
                binding.tvOrderNow.setBackgroundResource(R.drawable.custom_bg_social_round_16)
                binding.tvOrderNow.setTextColor(Color.parseColor("#494F58"))
                isClickOrderNow = false
            }
        }

        binding.tvChooseTime.setOnClickListener {
            if (isClickOrderNow) {
                binding.tvOrderNow.setBackgroundResource(R.drawable.custom_bg_social_round_16)
                binding.tvOrderNow.setTextColor(Color.parseColor("#494F58"))
                isClickOrderNow = false
            }

            val dialogBottomChooseTime : DialogBottomChooseTime = DialogBottomChooseTime(this)
            dialogBottomChooseTime.show(childFragmentManager, null)
        }

        binding.tvContinue.setOnClickListener {
//            val dialogWaitingFindWorker: DialogWaitingFindWorker = DialogWaitingFindWorker()
//            dialogWaitingFindWorker.show(childFragmentManager, "")

            val bundle: Bundle = Bundle()
            bundle.putString("EDT_PLACE", binding.edtSearchPlace.text.toString())
            bundle.putString("EDT_DESCRIBE", binding.edtDescribe.text.toString())

            bundle.putBoolean("IS_CLICK_ELECTRIC", isClickElectric)
            bundle.putBoolean("IS_CLICK_CLEAN", isClickClean)
            bundle.putBoolean("IS_CLICK_PIPE", isClickPipe)
            bundle.putBoolean("IS_CLICK_ELECTRIC_COLD", isClickElectricCold)

            bundle.putBoolean("IS_CLICK_1", isClick1)
            bundle.putBoolean("IS_CLICK_2", isClick2)
            bundle.putBoolean("IS_CLICK_3", isClick3)
            bundle.putBoolean("IS_CLICK_4", isClick4)
            bundle.putBoolean("IS_CLICK_5", isClick5)
            bundle.putBoolean("IS_CLICK_6", isClick6)

            if (!isShowDialog) {
                findingWorkerDialog = DialogWaitingFindWorker()
                findingWorkerDialog.show(childFragmentManager, null)
                isShowDialog = true;
            }

            Handler(Looper.getMainLooper()).postDelayed({
                navigateScreen(bundle, R.id.findingWorkerFragment)
//                findingWorkerDialog.dismiss()
            }, 3000)
        }

        binding.tvFix.setOnClickListener {
            if (!isClickElectricCold) {
                binding.tvFix.setBackgroundResource(R.drawable.custom_bg_ffa92e)
                binding.tvFix.setTextColor(resources.getColor(R.color.white))
                isClickElectricCold = true

                binding.tvCare.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvCare.setTextColor(Color.parseColor("#FFA92E"))
                isClickPipe = false

                binding.tvClean.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvClean.setTextColor(Color.parseColor("#FFA92E"))
                isClickClean = false

                binding.tvInstall.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvInstall.setTextColor(Color.parseColor("#FFA92E"))
                isClickElectric = false
            } else {
                binding.tvFix.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvFix.setTextColor(Color.parseColor("#FFA92E"))
                isClickElectricCold = false
            }
        }

        binding.tvCare.setOnClickListener {
            if (!isClickPipe) {
                binding.tvCare.setBackgroundResource(R.drawable.custom_bg_ffa92e)
                binding.tvCare.setTextColor(resources.getColor(R.color.white))
                isClickPipe = true

                binding.tvFix.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvFix.setTextColor(Color.parseColor("#FFA92E"))
                isClickElectricCold = false

                binding.tvClean.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvClean.setTextColor(Color.parseColor("#FFA92E"))
                isClickClean = false

                binding.tvInstall.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvInstall.setTextColor(Color.parseColor("#FFA92E"))
                isClickElectric = false
            } else {
                binding.tvCare.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvCare.setTextColor(Color.parseColor("#FFA92E"))
                isClickPipe = false
            }
        }

        binding.tvClean.setOnClickListener {
            if (!isClickClean) {
                binding.tvClean.setBackgroundResource(R.drawable.custom_bg_ffa92e)
                binding.tvClean.setTextColor(resources.getColor(R.color.white))
                isClickClean = true

                binding.tvFix.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvFix.setTextColor(Color.parseColor("#FFA92E"))
                isClickPipe = false

                binding.tvCare.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvCare.setTextColor(Color.parseColor("#FFA92E"))
                isClickElectricCold = false

                binding.tvInstall.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvInstall.setTextColor(Color.parseColor("#FFA92E"))
                isClickElectric = false
            } else {
                binding.tvClean.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvClean.setTextColor(Color.parseColor("#FFA92E"))
                isClickClean = false
            }
        }

        binding.tvInstall.setOnClickListener {
            if (!isClickElectric) {
                binding.tvInstall.setBackgroundResource(R.drawable.custom_bg_ffa92e)
                binding.tvInstall.setTextColor(resources.getColor(R.color.white))
                isClickElectric = true

                binding.tvFix.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvFix.setTextColor(Color.parseColor("#FFA92E"))
                isClickElectricCold = false

                binding.tvCare.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvCare.setTextColor(Color.parseColor("#FFA92E"))
                isClickPipe = false

                binding.tvClean.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvClean.setTextColor(Color.parseColor("#FFA92E"))
                isClickClean = false
            } else {
                binding.tvInstall.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvInstall.setTextColor(Color.parseColor("#FFA92E"))
                isClickElectric = false
            }
        }

        binding.tvFridge.setOnClickListener {
            if (!isClick1) {
                binding.tvFridge.setBackgroundResource(R.drawable.custom_bg_ffa92e)
                binding.tvFridge.setTextColor(resources.getColor(R.color.white))
                isClick1 = true

                binding.tvFreeze.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvFreeze.setTextColor(Color.parseColor("#FFA92E"))
                isClick2 = false

                binding.tvWashing.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvWashing.setTextColor(Color.parseColor("#FFA92E"))
                isClick3 = false

                binding.tvTelevision.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvTelevision.setTextColor(Color.parseColor("#FFA92E"))
                isClick4 = false

                binding.tvHotCold.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvHotCold.setTextColor(Color.parseColor("#FFA92E"))
                isClick5 = false

                binding.tvFan.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvFan.setTextColor(Color.parseColor("#FFA92E"))
                isClick6 = false
            } else {
                binding.tvFridge.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvFridge.setTextColor(Color.parseColor("#FFA92E"))
                isClick1 = false
            }
        }

        binding.tvFreeze.setOnClickListener {
            if (!isClick2) {
                binding.tvFreeze.setBackgroundResource(R.drawable.custom_bg_ffa92e)
                binding.tvFreeze.setTextColor(resources.getColor(R.color.white))
                isClick2 = true

                binding.tvFridge.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvFridge.setTextColor(Color.parseColor("#FFA92E"))
                isClick1 = false

                binding.tvWashing.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvWashing.setTextColor(Color.parseColor("#FFA92E"))
                isClick3 = false

                binding.tvTelevision.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvTelevision.setTextColor(Color.parseColor("#FFA92E"))
                isClick4 = false

                binding.tvHotCold.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvHotCold.setTextColor(Color.parseColor("#FFA92E"))
                isClick5 = false

                binding.tvFan.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvFan.setTextColor(Color.parseColor("#FFA92E"))
                isClick6 = false
            } else {
                binding.tvFreeze.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvFreeze.setTextColor(Color.parseColor("#FFA92E"))
                isClick2 = false
            }
        }

        binding.tvWashing.setOnClickListener {
            if (!isClick3) {
                binding.tvWashing.setBackgroundResource(R.drawable.custom_bg_ffa92e)
                binding.tvWashing.setTextColor(resources.getColor(R.color.white))
                isClick3 = true

                binding.tvFreeze.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvFreeze.setTextColor(Color.parseColor("#FFA92E"))
                isClick2 = false

                binding.tvFridge.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvFridge.setTextColor(Color.parseColor("#FFA92E"))
                isClick1 = false

                binding.tvTelevision.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvTelevision.setTextColor(Color.parseColor("#FFA92E"))
                isClick4 = false

                binding.tvHotCold.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvHotCold.setTextColor(Color.parseColor("#FFA92E"))
                isClick5 = false

                binding.tvFan.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvFan.setTextColor(Color.parseColor("#FFA92E"))
                isClick6 = false
            } else {
                binding.tvWashing.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvWashing.setTextColor(Color.parseColor("#FFA92E"))
                isClick3 = false
            }
        }

        binding.tvTelevision.setOnClickListener {
            if (!isClick4) {
                binding.tvTelevision.setBackgroundResource(R.drawable.custom_bg_ffa92e)
                binding.tvTelevision.setTextColor(resources.getColor(R.color.white))
                isClick4 = true

                binding.tvFreeze.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvFreeze.setTextColor(Color.parseColor("#FFA92E"))
                isClick2 = false

                binding.tvWashing.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvWashing.setTextColor(Color.parseColor("#FFA92E"))
                isClick3 = false

                binding.tvFridge.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvFridge.setTextColor(Color.parseColor("#FFA92E"))
                isClick1 = false

                binding.tvHotCold.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvHotCold.setTextColor(Color.parseColor("#FFA92E"))
                isClick5 = false

                binding.tvFan.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvFan.setTextColor(Color.parseColor("#FFA92E"))
                isClick6 = false
            } else {
                binding.tvTelevision.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvTelevision.setTextColor(Color.parseColor("#FFA92E"))
                isClick4 = false
            }
        }

        binding.tvHotCold.setOnClickListener {
            if (!isClick5) {
                binding.tvHotCold.setBackgroundResource(R.drawable.custom_bg_ffa92e)
                binding.tvHotCold.setTextColor(resources.getColor(R.color.white))
                isClick5 = true

                binding.tvFreeze.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvFreeze.setTextColor(Color.parseColor("#FFA92E"))
                isClick2 = false

                binding.tvWashing.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvWashing.setTextColor(Color.parseColor("#FFA92E"))
                isClick3 = false

                binding.tvTelevision.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvTelevision.setTextColor(Color.parseColor("#FFA92E"))
                isClick4 = false

                binding.tvFridge.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvFridge.setTextColor(Color.parseColor("#FFA92E"))
                isClick1 = false

                binding.tvFan.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvFan.setTextColor(Color.parseColor("#FFA92E"))
                isClick6 = false
            } else {
                binding.tvHotCold.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvHotCold.setTextColor(Color.parseColor("#FFA92E"))
                isClick5 = false
            }
        }

        binding.tvFan.setOnClickListener {
            if (!isClick6) {
                binding.tvFan.setBackgroundResource(R.drawable.custom_bg_ffa92e)
                binding.tvFan.setTextColor(resources.getColor(R.color.white))
                isClick6 = true

                binding.tvFreeze.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvFreeze.setTextColor(Color.parseColor("#FFA92E"))
                isClick2 = false

                binding.tvWashing.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvWashing.setTextColor(Color.parseColor("#FFA92E"))
                isClick3 = false

                binding.tvTelevision.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvTelevision.setTextColor(Color.parseColor("#FFA92E"))
                isClick4 = false

                binding.tvHotCold.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvHotCold.setTextColor(Color.parseColor("#FFA92E"))
                isClick5 = false

                binding.tvFridge.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvFridge.setTextColor(Color.parseColor("#FFA92E"))
                isClick1 = false
            } else {
                binding.tvFan.setBackgroundResource(R.drawable.custom_bg_fff5e5)
                binding.tvFan.setTextColor(Color.parseColor("#FFA92E"))
                isClick6 = false
            }
        }
    }

    override fun onPause() {
        super.onPause()
        if (isShowDialog) {
            findingWorkerDialog.dismissAllowingStateLoss()
            isShowDialog = false
        }
    }

    override fun onPermissionGranted() {

    }

    override fun observerData() {

    }

    override fun onConfirmTime(time: String) {
        binding.tvChooseTime.text = time
        binding.tvChooseTime.setTextColor(Color.WHITE)
        binding.tvChooseTime.setBackgroundResource(R.drawable.custom_bg_ffa92e_round_16)
    }
}