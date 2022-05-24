package com.ihediohachinedu.accessimageapp.repo

import com.ihediohachinedu.accessimageapp.data.UserDAO
import com.ihediohachinedu.accessimageapp.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepo @Inject constructor(private val userDAO: UserDAO) {

    //insert user details to room
    suspend fun createUserRecords(user: User) : Long {
        return userDAO.insertToRoomDatabase(user)
    }

    //get single user details e.g with id 1
    val getUserDetails: Flow<List<User>> get() =  userDAO.getUserDetails()

    //delete single user record
    suspend fun deleteSingleUserRecord() {
        userDAO.deleteSingleUserDetails(1)
    }
}