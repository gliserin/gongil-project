package com.odhs.gongilproject.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.odhs.gongilproject.R
import com.odhs.gongilproject.apiWeather
import com.odhs.gongilproject.data.BaseTime
import com.odhs.gongilproject.data.Value
import com.odhs.gongilproject.model.forecastSpace.ForecastSpace
import com.odhs.gongilproject.request
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    private var running = true

    private lateinit var baseTime: BaseTime

    private lateinit var changeDotJob: Job


    private var rainProbability: Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* init */
        baseTime = BaseTime()
        /* init */


        changeDotJob = changeDot()


        weatherLoad()

    }

    private fun displayResult() {
        GlobalScope.launch {
            delay(3000L)
            runOnUiThread {
                layout_main_loading.visibility = View.GONE
                layout_main_notice.visibility = View.VISIBLE

                text_main_outsidePercent.text = (100 - rainProbability).toInt().toString() + "%"
                text_main_insidePercent.text = rainProbability.toInt().toString() + "%"
            }
        }
    }

    private fun weatherLoad() {

        Log.d("baseTime", baseTime.getBaseDate())
        Log.d("baseTime", baseTime.getBaseTime())

        val onFailLoading = {
            runOnUiThread {
                layout_main_loading.visibility = View.GONE
                text_main_error.text = getString(R.string.error_message_404)
                text_main_error.visibility = View.VISIBLE
                changeDotJob.cancel()
            }
        }

        val onFailConnecting = {
            runOnUiThread {
                layout_main_loading.visibility = View.GONE
                text_main_error.text = getString(R.string.error_message_fail)
                text_main_error.visibility = View.VISIBLE
                changeDotJob.cancel()
            }
        }

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
//                Log.d("code", it.code().toString())
//                Log.d("code", it.message())
//                text_main_log.text = it.body()!!.toString()
                Log.d("code", it.raw().request().url().toString())


                if(it.code() == 200 && it.body()!!.response.header.msg == "OK") {
                    val items = it.body()!!.response.body.items.itemArray
                    for(item in items) {
//                        text_main_log.text = ForecastSpace.RAIN_PROBABILITY + baseTime.getBaseDate(1) + getString(R.string.find_time)
                        if(item.category == ForecastSpace.RAIN_PROBABILITY && item.forecastDate == baseTime.getBaseDate(1) && item.forecastTime == getString(R.string.find_time)) {
                            rainProbability = item.forecastValue
                            break
                        }
                    }
                    displayResult()
                } else {
                    onFailLoading.invoke()
                }
            },
            fail = {
//                Log.d("code", "fail")
                onFailConnecting.invoke()
            }
        )
    }

    private fun changeDot(): Job {
        var count = 1

        val job = GlobalScope.launch {
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

        job.invokeOnCompletion {
            runOnUiThread {
                text_main_loading1.visibility = View.INVISIBLE
                text_main_loading2.visibility = View.INVISIBLE
                text_main_loading3.visibility = View.INVISIBLE
            }
        }

        return job
    }


}
