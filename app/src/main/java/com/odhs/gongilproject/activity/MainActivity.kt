package com.odhs.gongilproject.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.odhs.gongilproject.R
import com.odhs.gongilproject.apiWeather
import com.odhs.gongilproject.data.Value
import com.odhs.gongilproject.getToday
import com.odhs.gongilproject.request
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    private var running = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        changeDot()
        weatherLoad()
    }

    private fun weatherLoad(): Job {
        val job = GlobalScope.launch {

        }

        val call = apiWeather.forecastSpace(getToday(), "00:00", Value.waDongX, Value.waDongY, 300, 1, "json", getString(R.string.auth_key))

//        text_main_log.text = getString(R.string.auth_key) + getToday() + "00:00" + Value.waDongX + Value.waDongY + 300 + 1 + "json"

        request(
            call = call,
            success = {
                Log.d("code", it.code().toString())
                Log.d("code", it.message())
                text_main_log.text = it.body()!!.toString()
            },
            fail = {
                Log.d("code", "fail")
            }
        )

        return job
    }

    private fun changeDot() {
        var count = 1

        GlobalScope.launch {
            while(running) {
                yield()
                runOnUiThread {
                    when(count) {
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
                    if(count == 4) count = 1
                }
                delay(500L)
            }
        }
    }



}
