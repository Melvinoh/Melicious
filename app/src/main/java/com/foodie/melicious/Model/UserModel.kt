package com.foodie.melicious.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
class UserModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "profilePic") val profileImgUri: String? = null,
    @ColumnInfo(name = "fullName")val fullName : String,
    @ColumnInfo(name = "email")val emailAddress : String,
    @ColumnInfo(name = "mobile")val mobile : String,
    @ColumnInfo(name = "country")val country : String,
    @ColumnInfo(name = "location")val location1 : String
)
