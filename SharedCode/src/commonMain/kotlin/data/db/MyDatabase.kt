package data.db

import com.squareup.sqldelight.ColumnAdapter
import com.squareup.sqldelight.db.SqlDriver
import db.ChatFullModel
import db.ChatShortModel
import org.kotlin.mpp.mobile.data.entity.Message

fun encodeOne(mes: Message): String {
    return "${mes.text}$${mes.author}$${mes.created_at}$${mes.is_read}|"
}
fun decodeOne(str: String): Message {
    val values = str.split('$')
    return Message(values[0], values[1], values[2], values[3].toBoolean())
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
    return MyDatabase(
        driver, ChatFullModel.Adapter(
            messagesAdapter = messagesAdapter
        ), ChatShortModel.Adapter(
            usersAdapter = usersAdapter
        )
    )
}