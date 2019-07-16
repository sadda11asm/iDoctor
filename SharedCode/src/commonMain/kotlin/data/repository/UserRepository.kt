package data.repository

import data.api.UserApi
import data.db.UserDao
import data.entity.UserFull
import org.kotlin.mpp.mobile.util.log

class UserRepository(
    private val userApi: UserApi,
    private val userDao: UserDao
) {

    suspend fun getUserInfo(token: String): UserFull {
        log("UserRepository", "dsgsdfg")
        val user = userApi.getUserInfo(token).data
        log("UserRepository", user.toString())
        insertToDb(user)
        return user
    }


    private fun insertToDb(user: UserFull) {
       userDao.insert(user)
    }
}