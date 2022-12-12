package com.va.workercall.ui.main.receipt

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.va.workercall.common.models.Receipt
import com.va.workercall.common.models.WorkerInfo
import com.va.workercall.data.repository.WorkerRepository
import com.va.workercall.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ReceiptViewModel @Inject constructor(
    private val workerRepository: WorkerRepository
): BaseViewModel() {
    var listReceiptLiveData = MutableLiveData<MutableList<Receipt>>()

    fun getListReceiptInfo() {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            run {
                Timber.e(throwable)
            }
        }) {
            listReceiptLiveData.postValue(workerRepository.getListReceiptInfo())
        }
    }
}