package com.ihediohachinedu.roomdb.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = PersonalData.TABLE_NAME)
data class PersonalData(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    var userID: Int? = null,
    @ColumnInfo(name = NAME)
    var fullName: String? = null ,
    @ColumnInfo(name = AGE)
    var ageTotal: Int? = null

) {
    companion object{
        const val TABLE_NAME = "personal_details"
        const val ID = "id"
        const val NAME = "name"
        const val AGE = "age"
    }
}