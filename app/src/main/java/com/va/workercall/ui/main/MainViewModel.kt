package com.va.workercall.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
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
class MainViewModel @Inject constructor(
    private val workerRepository: WorkerRepository
) : BaseViewModel() {
    var listWorkerLiveData = MutableLiveData<MutableList<WorkerInfo>>()

    fun getListWorkerInfo() {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            run {
                Timber.e(throwable)
            }
        }) {
            listWorkerLiveData.postValue(workerRepository.getListWorkerInfo())
        }
    }
}