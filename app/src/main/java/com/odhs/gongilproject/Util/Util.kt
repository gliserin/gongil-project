package com.odhs.gongilproject.Util

import android.content.Context
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author 오동호 <graystone117@gmail.com>
 * @since 2019-06-09
 */

object Util {
    fun getToday(): String {
        val format = SimpleDateFormat("yyyyMMdd", Locale.KOREA)
        val time = Calendar.getInstance()
        return format.format(time.time)
    }

    fun getBaseTime(): String {
        val format = SimpleDateFormat("HHmm", Locale.KOREA)
        val time = Calendar.getInstance()
        return "0"
    }
}