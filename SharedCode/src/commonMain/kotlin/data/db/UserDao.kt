package data.db

import com.squareup.sqldelight.Query
import data.entity.*
import db.ChatShortModel
import org.kotlin.mpp.mobile.util.log

class UserDao(database: MyDatabase) {

    private val dbUser = database.userModelQueries

    internal fun insert(user: UserFull) {
        dbUser.insertItem(
            user.id.toLong(),
            user.firstName,
            user.lastName,
            user.patronymic,
            user.phone,
            user.email,
            user.createdAt,
            user.updatedAt,
            user.doctorId
        )
    }


    internal fun selectAll(): List<UserFull> {
        val resp = dbUser.selectAll()
            .executeAsList()
        val users  = mutableListOf<UserFull>()
        for (user in resp)
            users.add(user.to())
        return users
    }

    internal fun selectUser(id: Int): UserFull {
        return dbUser.selectById(id.toLong()).executeAsOne().to()
    }
}