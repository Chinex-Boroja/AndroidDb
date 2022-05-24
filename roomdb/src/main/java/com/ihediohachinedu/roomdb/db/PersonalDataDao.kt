package com.ihediohachinedu.roomdb.db

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface PersonalDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPersonData(data: PersonalData) : Completable

    @Query("SELECT * FROM ${PersonalData.TABLE_NAME}")
    fun getAllRecords(): Single<List<PersonalData>>

    @Delete
    fun deletePersonalData(personalData: PersonalData) : Completable

    @Update
    fun updatePersonalData(personalData: PersonalData)
}