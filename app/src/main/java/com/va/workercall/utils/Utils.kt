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
        workerList.add(WorkerInfo(1, "Nguyễn Minh Cường", 200000, 4.5f, "Cầu Giấy", "Bảo dưỡng điều hòa treo tường", listOf("Điện lạnh", "Tủ lạnh", "Điện")))
        workerList.add(WorkerInfo(2, "Nguyễn Tiến Kỳ", 190000, 4.5f, "Cầu Giấy", "Bảo dưỡng điều hòa treo tường", listOf("Điện lạnh", "Tủ lạnh", "Điện")))
        workerList.add(WorkerInfo(3, "Nguyễn Xuân Hòa", 180000, 4.5f, "Cầu Giấy", "Bảo dưỡng điều hòa treo tường", listOf("Điện lạnh", "Tủ lạnh", "Điện")))
        workerList.add(WorkerInfo(4, "Nguyễn Hoàng Long", 150000, 4.5f, "Cầu Giấy", "Bảo dưỡng điều hòa treo tường", listOf("Điện lạnh", "Tủ lạnh", "Điện")))

        return workerList
    }

    fun getListReceipt(): MutableList<Receipt> {
        val receiptList: MutableList<Receipt> = ArrayList()
        receiptList.add(Receipt(123123, 1, "Nguyễn Mạnh", "Sửa chữa tủ lạnh", "Hôm nay, 08:30 - 10:30", "45, Trần Thái Tông"))
        receiptList.add(Receipt(234324, 2, "Nguyễn Xuân Hòa", "Sửa chữa máy giặt", "12/12/2022, 12:30 - 14:30", "Phù Lỗ, Sóc Sơn"))
        receiptList.add(Receipt(456565, 3, "Trần Minh", "Sửa chữa điều hòa", "20/12/2022, 08:30 - 10:30", "45, Trần Nhật Duật"))
        receiptList.add(Receipt(789898, 4, "Phạm Quyết", "Sửa chữa ống nước", "Ngày mai, 08:30 - 10:30", "45, Cầu Giấy"))

        return receiptList
    }

    fun getListReceiptByWorker(): MutableList<Receipt> {
        val receiptList: MutableList<Receipt> = ArrayList()
        receiptList.add(Receipt(123123, 1, "Đỗ Phương", "Sửa chữa tủ lạnh", "Hôm nay, 08:30 - 10:30", "45, Trần Thái Tông"))
        receiptList.add(Receipt(234324, 2, "Nguyễn Vân Anh", "Sửa chữa tủ lạnh", "12/12/2022, 12:30 - 14:30", "Phù Lỗ, Sóc Sơn"))
        receiptList.add(Receipt(456565, 3, "Trần Hoàng Minh", "Sửa chữa tủ lạnh", "20/12/2022, 08:30 - 10:30", "45, Trần Nhật Duật"))
        receiptList.add(Receipt(789898, 4, "Cao Thanh Tâm", "Sửa chữa tủ lạnh", "Ngày mai, 08:30 - 10:30", "45, Cầu Giấy"))

        return receiptList
    }
}