package util


expect val getYear: Int

expect val currentTime: String

expect fun getTimeZoneOffset(): Long

expect fun convertTime(time: String?): String

