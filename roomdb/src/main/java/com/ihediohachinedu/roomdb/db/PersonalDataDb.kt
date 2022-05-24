package com.ihediohachinedu.roomdb.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PersonalData::class], version = DB_VERSION)
abstract class PersonalDataDb: RoomDatabase() {

    abstract fun personalDataDao(): PersonalDataDao

    companion object {
        @Volatile
        private var databaseInstance: PersonalDataDb? = null

        fun getDatabaseInstance(context: Context): PersonalDataDb =
            databaseInstance ?: synchronized(this) {
                databaseInstance ?: buildDatabaseInstance(context).also {
                    databaseInstance = it
                }
            }

        private fun buildDatabaseInstance(context: Context) =
            Room.databaseBuilder(context, PersonalDataDb::class.java, DB_NAME)
                .fallbackToDestructiveMigration().build()
    }
}

const val DB_NAME = "PersonalDataSample.db"
const val DB_VERSION = 1