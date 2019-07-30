package util

import android.annotation.TargetApi
import android.os.Build
import android.text.format.DateUtils
import androidx.annotation.RequiresApi
import org.kotlin.mpp.mobile.util.log
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit

actual val getYear: Int
    get() = Calendar.getInstance().get(Calendar.YEAR)

actual val currentTime: String
    get() {
        // TODO better implementation?
        val pattern = "yyyy-MM-dd'T'kk:mm:ss"
        val simpleDateFormat = SimpleDateFormat(pattern, Locale.US)
        return simpleDateFormat.format(Date())
    }
actual fun getTimeZoneOffset(): Long {
    var mCalendar: Calendar = GregorianCalendar()
    val mTimeZone = mCalendar.timeZone
    val offset = mTimeZone.rawOffset.toLong()
    return TimeUnit.HOURS.convert(offset, TimeUnit.MILLISECONDS)
}

actual fun convertTime(time: String?): String {
    val pattern = "yyyy-MM-dd'T'kk:mm:ss" // TODO return back:
    val date = try {
         SimpleDateFormat(pattern, Locale.US).parse(time)
    } catch (e: Exception) {
        SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.US).parse(time)
    }
    val newDate = SimpleDateFormat(pattern, Locale.US).format(date.time + getTimeZoneOffset() * 60 * 60 * 1000)
    return newDate.toString()
}