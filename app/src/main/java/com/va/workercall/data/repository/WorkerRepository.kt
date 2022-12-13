package com.va.workercall.data.repository

import com.va.workercall.common.models.Receipt
import com.va.workercall.common.models.WorkerInfo
import com.va.workercall.data.local.SharedPreferenceHelper
import com.va.workercall.utils.Utils
import javax.inject.Inject

class WorkerRepository @Inject constructor(var sharedPreferenceHelper: SharedPreferenceHelper) {
    fun getListWorkerInfo(): MutableList<WorkerInfo> {
        return Utils.getListWorkerInfo()
    }

    fun getListReceiptInfo(): MutableList<Receipt> {
        return Utils.getListReceipt()
    }
    fun getListReceiptByWorkerInfo(): MutableList<Receipt> {
        return Utils.getListReceiptByWorker()
    }
}