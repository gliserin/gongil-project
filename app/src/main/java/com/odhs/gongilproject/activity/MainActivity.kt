package com.odhs.gongilproject.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.odhs.gongilproject.R
import com.odhs.gongilproject.apiWeather
import com.odhs.gongilproject.data.BaseTime
import com.odhs.gongilproject.data.Value
import com.odhs.gongilproject.request
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    private var running = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        changeDot()
        weatherLoad()

    }

    private fun weatherLoad() {

        val baseTime = BaseTime()

        Log.d("baseTime", baseTime.getBaseDate())
        Log.d("baseTime", baseTime.getBaseTime())

        val call = apiWeather.forecastSpace(
            getString(R.string.auth_key),
            baseTime.getBaseDate(),
            baseTime.getBaseTime(),
            Value.waDongX,
            Value.waDongY,
            300,
            1,
            "json"
        )

        request(
            call = call,
            success = {
                Log.d("code", it.code().toString())
                Log.d("code", it.message())
                text_main_log.text = it.body()!!.toString()

                Log.d("code", it.raw().request().url().toString())
            },
            fail = {
                Log.d("code", "fail")
            }
        )

    }

    private fun changeDot() {
        var count = 1

        GlobalScope.launch {
            while (running) {
                yield()
                runOnUiThread {
                    when (count) {
                        1 -> {
                            text_main_loading1.visibility = View.VISIBLE
                            text_main_loading2.visibility = View.INVISIBLE
                            text_main_loading3.visibility = View.INVISIBLE
                        }
                        2 -> {
                            text_main_loading1.visibility = View.VISIBLE
                            text_main_loading2.visibility = View.VISIBLE
                            text_main_loading3.visibility = View.INVISIBLE
                        }
                        3 -> {
                            text_main_loading1.visibility = View.VISIBLE
                            text_main_loading2.visibility = View.VISIBLE
                            text_main_loading3.visibility = View.VISIBLE
                        }
                    }
                    count++
                    if (count == 4) count = 1
                }
                delay(500L)
            }
        }
    }


}
