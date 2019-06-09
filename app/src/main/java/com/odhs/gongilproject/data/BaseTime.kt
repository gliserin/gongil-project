package com.odhs.gongilproject.data

import java.text.SimpleDateFormat
import java.util.*

/**
 * @author 오동호 <graystone117@gmail.com>
 * @since 2019-06-09
 */

class BaseTime {

    private var baseTime: String
    private var baseDate: String

    private val cal: Calendar = Calendar.getInstance()
    private val format1 = SimpleDateFormat("HH", Locale.KOREA)
    private val format2 = SimpleDateFormat("yyyyMMdd", Locale.KOREA)

    fun getBaseTime(): String = baseTime
    fun getBaseDate(): String = baseDate

    fun getBaseDate(plusDate: Int): String {
        val cal = this.cal.clone() as Calendar
        cal.add(Calendar.DATE, plusDate)
        return format2.format(cal.time)
    }

    init {
        /* 시간 픽스 작업 */
        val date = Date()
        cal.time = date
        cal.add(Calendar.HOUR, 9)
        /* 시간 픽스 작업 */

        val format1Result = format1.format(cal.time).toInt()


        baseDate = format2.format(cal.time)

        when (format1Result) {
            0, 1, 2 -> {
                baseTime = "2300"
                cal.add(Calendar.DATE, -1)
                baseDate = format2.format(cal.time)
            }
            3, 4, 5 -> {
                baseTime = "0200"
            }
            6, 7, 8 -> {
                baseTime = "0500"
            }
            9, 10, 11 -> {
                baseTime = "0800"
            }
            12, 13, 14 -> {
                baseTime = "1100"
            }
            15, 16, 17 -> {
                baseTime = "1400"
            }
            18, 19, 20 -> {
                baseTime = "1700"
            }
            21, 22, 23 -> {
                baseTime = "2000"
            }
            else -> {
                baseTime = "0200"
            }
        }
    }
}