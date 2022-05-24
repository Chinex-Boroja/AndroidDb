package com.ihediohachinedu.accessimageapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ihediohachinedu.accessimageapp.data.UserDAO
import com.ihediohachinedu.accessimageapp.global.ApplicationScope
import com.ihediohachinedu.accessimageapp.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [User::class],  version = 1)

abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDAO
    class Callback @Inject constructor(private val userDb: Provider<UserDatabase>, @ApplicationScope private val applicationScope: CoroutineScope)
        : androidx.room.RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            val dao = userDb.get().userDao()
            applicationScope.launch {
            }
        }
    }
}