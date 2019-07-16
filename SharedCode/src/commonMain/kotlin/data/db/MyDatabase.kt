package data.db

import com.squareup.sqldelight.ColumnAdapter
import com.squareup.sqldelight.db.SqlDriver
import data.entity.EventDate
import db.ChatFullModel
import db.ChatShortModel
import db.UserModel
import org.kotlin.mpp.mobile.data.entity.Message

fun encodeOne(mes: Message): String {
    return "${mes.text}$${mes.createdAt}$${mes.updatedAt}$${mes.text}|"
}
fun decodeOne(str: String): Message {
    val values = str.split('$')
    return Message(values[0].toInt(), values[1].toInt(), values[2].toInt(), values[3], values[4], values[5])
}

fun createDatabase(driver: SqlDriver): MyDatabase {
    val messagesAdapter = object : ColumnAdapter<List<Message>, String> {
        override fun decode(databaseValue: String): List<Message> {
            if (databaseValue.isEmpty()) return emptyList()
            val msgs = databaseValue.split('|')
            val listOfMessages = mutableListOf<Message>()
            for (msg in msgs) {
                listOfMessages.add(decodeOne(msg))
            }
            return listOfMessages
        }

        override fun encode(msgs: List<Message>): String {
            if (msgs.isEmpty()) return ""
            var ans=""
            for (msg in msgs) {
                ans+=encodeOne(msg)
            }
            ans.dropLast(1)
            return ans
        }
    }

    val usersAdapter = object : ColumnAdapter<List<Int>, String> {
        override fun decode(databaseValue: String): List<Int> {
            val list:MutableList<Int> = mutableListOf()
            val arr = databaseValue.split(',')
            for (value in arr)
                if (value.isNotEmpty())
                    list.add(value.toInt())
            return list
        }

        override fun encode(msgs: List<Int>): String {
            var ans=""
            for (msg in msgs){
                ans+=msg.toString()
                ans+=","
            }
            ans.dropLast(1)
            return ans
        }
    }

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
        driver, ChatFullModel.Adapter(
            messagesAdapter = messagesAdapter
        ), ChatShortModel.Adapter(
            usersAdapter = usersAdapter
        ), UserModel.Adapter(
            created_atAdapter = createdAtAdapter,
            updated_atAdapter = updatedAtAdapter
        )
    )
}