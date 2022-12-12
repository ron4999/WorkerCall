package com.va.workercall.ui.main.workerinfo

import android.os.Bundle
import android.view.View
import com.va.workercall.R
import com.va.workercall.databinding.FragmentWorkerInfoBinding
import com.va.workercall.ui.base.BaseBindingFragment
import com.va.workercall.ui.dialog.DialogSendRequest
import com.va.workercall.ui.main.MainViewModel

class WorkerInfoFragment : BaseBindingFragment<FragmentWorkerInfoBinding, MainViewModel>(), DialogSendRequest.OnClickListener {
    private var isFavorite: Boolean = false
    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val layoutId: Int
        get() = R.layout.fragment_worker_info

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        onClickListener()
    }

    private fun onClickListener() {
        binding.ivBack.setOnClickListener {
            popBackStack()
        }

        binding.ivFavorite.setOnClickListener {
            if (!isFavorite) {
                binding.ivFavorite.setImageDrawable(requireContext().getDrawable(R.drawable.ic_favorite_choose))
                isFavorite = true;
            } else {
                binding.ivFavorite.setImageDrawable(requireContext().getDrawable(R.drawable.ic_favorite))
                isFavorite = false;
            }
        }

        binding.tvSendRequest.setOnClickListener {
            val dialogSendRequest: DialogSendRequest = DialogSendRequest(this)
            dialogSendRequest.show(childFragmentManager, null)
        }

        binding.ivMessage.setOnClickListener {
            navigateScreen(null, R.id.messageFragment)
        }
    }

    override fun onPermissionGranted() {

    }

    override fun observerData() {

    }

    override fun onClickLater() {
        popBackStackWithInclusive(R.id.homeFragment, false)
    }

    override fun onClickMessage() {
        navigateScreen(null, R.id.messageFragment)
    }
}