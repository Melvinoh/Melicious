package com.foodie.melicious.Helper

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.foodie.melicious.Model.UserModel

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(userModel: UserModel)

    @Delete
    suspend fun deleteUser(userModel: UserModel)

    @Update
    suspend fun updateUser(userModel: UserModel)

    @Query("SELECT * FROM user_profile WHERE id = :userId")
     suspend fun getUser(userId :Int) : List< UserModel>
}