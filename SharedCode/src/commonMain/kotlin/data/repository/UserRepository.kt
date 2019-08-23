package data.repository

import data.api.UserApi
import data.db.UserDao
import data.entity.UserEditResponse
import data.entity.UserFull
import org.kotlin.mpp.mobile.data.entity.UserEditRequest
import org.kotlin.mpp.mobile.domain.usecases.EditUserInfo
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

    fun selectFromDb(userId: Int): UserFull {
        return userDao.selectUser(userId)
    }


    suspend fun editUserInfo(request: UserEditRequest): UserFull {
        val response = userApi.editUserInfo(request)
        insertToDb(response)
        return response
    }
}