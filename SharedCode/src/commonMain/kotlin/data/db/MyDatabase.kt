package data.db

import com.squareup.sqldelight.ColumnAdapter
import com.squareup.sqldelight.db.SqlDriver
import data.entity.EventDate
import db.ChatFullModel
import db.ChatShortModel
import db.UserModel
import org.kotlin.mpp.mobile.data.entity.ChatFullResponse
import org.kotlin.mpp.mobile.data.entity.Message


fun createDatabase(driver: SqlDriver): MyDatabase {

    val createdAtAdapter = object : ColumnAdapter<EventDate, String> {
        override fun decode(databaseValue: String): EventDate {
            val vals = databaseValue.split(',')
            return EventDate(vals[0], vals[1].toInt(), vals[2])
        }

        override fun encode(date: EventDate): String {
            val ans = date.date+","+date.timezoneType+","+date.timezone
            return ans
        }
    }
    val updatedAtAdapter = object : ColumnAdapter<EventDate, String> {
        override fun decode(databaseValue: String): EventDate {
            val vals = databaseValue.split(',')
            return EventDate(vals[0], vals[1].toInt(), vals[2])
        }

        override fun encode(date: EventDate): String {
            val ans = date.date+","+date.timezoneType+","+date.timezone
            return ans
        }
    }
    return MyDatabase(
        driver, UserModel.Adapter(
            created_atAdapter = createdAtAdapter,
            updated_atAdapter = updatedAtAdapter
        )
    )
}