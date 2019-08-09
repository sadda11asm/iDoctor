package org.kotlin.mpp.mobile.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleResponse(
    @SerialName("doctor_jobs")
    val doctorJobs: DoctorJob
)

@Serializable
data class DoctorJob(
    @SerialName("id")
    val doctorId: Int,
    val jobs: List<Job>
)

@Serializable
data class Job(
    val id: Int,
    val schedule: Schedule?
)

@Serializable
data class Schedule(
    val days: List<Day>
)

@Serializable
data class Day(
    val day: Int,
    val text: String,
    val times: List<Time>
)

@Serializable
data class Time(
    val key: Int,
    val isWork: Boolean
)