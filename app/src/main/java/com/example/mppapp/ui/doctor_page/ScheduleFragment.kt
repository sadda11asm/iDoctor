package com.example.mppapp.ui.doctor_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mppapp.R
import com.example.mppapp.util.getAccessToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.kotlin.mpp.mobile.ServiceLocator
import org.kotlin.mpp.mobile.data.entity.ScheduleRequest
import org.kotlin.mpp.mobile.domain.defaultDispatcher
import org.kotlin.mpp.mobile.domain.usecases.GetSchedule
import kotlinx.android.synthetic.main.fragment_doctor_schedule.*
import org.kotlin.mpp.mobile.data.entity.ScheduleResponse
import org.kotlin.mpp.mobile.util.log


class ScheduleFragment : Fragment()  {

    private val scope by lazy { CoroutineScope(defaultDispatcher) }


    val getSchedule = ServiceLocator.getSchedule
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_doctor_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments!!.getInt("id")
        scope.launch {
            getSchedule(
                params = ScheduleRequest(id, getAccessToken()),
                onSuccess = { setData(it); log("ScheduleFragment", it.toString()) },
                onFailure = { log("ScheduleFragment", it.toString()) }

            )
        }

    }


    private fun setData(scheduleResponse: ScheduleResponse) {
        val days = scheduleResponse.doctorJobs.jobs[0].schedule?.days

        if (days==null) {
            monday.text = "Просим уточнять по телефону"
            tuesday.visibility = View.INVISIBLE
            wednesday.visibility = View.INVISIBLE
            thursday.visibility = View.INVISIBLE
            friday.visibility = View.INVISIBLE
            saturday.visibility = View.INVISIBLE
            sunday.visibility = View.INVISIBLE
            return
        }
        for (day in days) {
            val times = day.times
            val periods = MutableList(25) { false }
            for (time in times) {
                periods[time.key] = time.isWork
            }
            var finalPeriod = " "
            var i = 0
            while (i < 25) {
                if (periods[i]) {
                    val start = i
                    while (periods[i]) {
                        i++
                    }
                    val finish = i-1
                    if (start!=finish) {
                        if (finalPeriod.length!=1) finalPeriod+=", "
                        finalPeriod+="$start - $finish"
                    }
                }
                i++
            }
            if (finalPeriod.isEmpty()) finalPeriod = "Выходной"
            when (day.day) {
                1 -> monday.append(finalPeriod)
                2 -> tuesday.append(finalPeriod)
                3 -> wednesday.append(finalPeriod)
                4 -> thursday.append(finalPeriod)
                5 -> friday.append(finalPeriod)
                6 -> saturday.append(finalPeriod)
                7 -> sunday.append(finalPeriod)
            }
        }
    }



}
