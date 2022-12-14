package com.va.workercall.utils

import android.content.Context
import android.content.res.Resources
import android.net.ConnectivityManager
import android.os.Build
import android.util.SparseArray
import com.va.workercall.common.models.Receipt
import com.va.workercall.common.models.WorkerInfo
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    fun getTimeString(time: Long): String {
        val date = Date(time)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.getDefault())
        return dateFormat.format(date)
    }

    fun getScreenWidth(context: Context): Int {
        val displaymetrics = context.resources.displayMetrics
        return displaymetrics.widthPixels
    }

    fun getScreenHeight(context: Context): Int {
        val displaymetrics = context.resources.displayMetrics
        return displaymetrics.heightPixels
    }

    fun isOnline(context: Context?): Boolean {
        if (context == null) {
            return false
        }
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectivityManager.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }

    fun <C> asList(sparseArray: SparseArray<C>?): List<C>? {
        if (sparseArray == null) return null
        val arrayList: MutableList<C> = ArrayList(sparseArray.size())
        for (i in 0 until sparseArray.size()) arrayList.add(sparseArray.valueAt(i))
        return arrayList
    }

    fun pxToDp(px: Float): Int {
        return (px / Resources.getSystem().displayMetrics.density).toInt()
    }

    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

    val localeCompat: Locale
        get() {
            val configuration = Resources.getSystem().configuration
            return if (isAtLeastSdkVersion(Build.VERSION_CODES.N)) configuration.locales[0] else configuration.locale
        }

    fun isAtLeastSdkVersion(versionCode: Int): Boolean {
        return Build.VERSION.SDK_INT >= versionCode
    }

    fun getListWorkerInfo(): MutableList<WorkerInfo> {
        val workerList: MutableList<WorkerInfo> = ArrayList()
        workerList.add(WorkerInfo(1, "Nguy???n Minh C?????ng", 200000, 4.5f, "C???u Gi???y", "B???o d?????ng ??i???u h??a treo t?????ng", listOf("??i???n l???nh", "T??? l???nh", "??i???n")))
        workerList.add(WorkerInfo(2, "Nguy???n Ti???n K???", 190000, 4.5f, "C???u Gi???y", "B???o d?????ng ??i???u h??a treo t?????ng", listOf("??i???n l???nh", "T??? l???nh", "??i???n")))
        workerList.add(WorkerInfo(3, "Nguy???n Xu??n H??a", 180000, 4.5f, "C???u Gi???y", "B???o d?????ng ??i???u h??a treo t?????ng", listOf("??i???n l???nh", "T??? l???nh", "??i???n")))
        workerList.add(WorkerInfo(4, "Nguy???n Ho??ng Long", 150000, 4.5f, "C???u Gi???y", "B???o d?????ng ??i???u h??a treo t?????ng", listOf("??i???n l???nh", "T??? l???nh", "??i???n")))

        return workerList
    }

    fun getListReceipt(): MutableList<Receipt> {
        val receiptList: MutableList<Receipt> = ArrayList()
        receiptList.add(Receipt(123123, 1, "Nguy???n M???nh", "S???a ch???a t??? l???nh", "H??m nay, 08:30 - 10:30", "45, Tr???n Th??i T??ng"))
        receiptList.add(Receipt(234324, 2, "Nguy???n Xu??n H??a", "S???a ch???a m??y gi???t", "12/12/2022, 12:30 - 14:30", "Ph?? L???, S??c S??n"))
        receiptList.add(Receipt(456565, 3, "Tr???n Minh", "S???a ch???a ??i???u h??a", "20/12/2022, 08:30 - 10:30", "45, Tr???n Nh???t Du???t"))
        receiptList.add(Receipt(789898, 4, "Ph???m Quy???t", "S???a ch???a ???ng n?????c", "Ng??y mai, 08:30 - 10:30", "45, C???u Gi???y"))

        return receiptList
    }

    fun getListReceiptByWorker(): MutableList<Receipt> {
        val receiptList: MutableList<Receipt> = ArrayList()
        receiptList.add(Receipt(123123, 1, "????? Ph????ng", "S???a ch???a t??? l???nh", "H??m nay, 08:30 - 10:30", "45, Tr???n Th??i T??ng"))
        receiptList.add(Receipt(234324, 2, "Nguy???n V??n Anh", "S???a ch???a t??? l???nh", "12/12/2022, 12:30 - 14:30", "Ph?? L???, S??c S??n"))
        receiptList.add(Receipt(456565, 3, "Tr???n Ho??ng Minh", "S???a ch???a t??? l???nh", "20/12/2022, 08:30 - 10:30", "45, Tr???n Nh???t Du???t"))
        receiptList.add(Receipt(789898, 4, "Cao Thanh T??m", "S???a ch???a t??? l???nh", "Ng??y mai, 08:30 - 10:30", "45, C???u Gi???y"))

        return receiptList
    }
}