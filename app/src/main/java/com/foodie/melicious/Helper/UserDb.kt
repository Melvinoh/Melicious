package com.foodie.melicious.Helper

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.foodie.melicious.Model.UserModel

@Database(entities = [UserModel::class], version = 1)
abstract class UserDb : RoomDatabase() {

    abstract fun UserDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserDb? = null

        fun getDatabase(context: Context): UserDb {
            if (INSTANCE == null) {
                synchronized(UserDb::class) {
                    INSTANCE = buildRoomDB(context)
                }
            }
            return INSTANCE!!
        }
        private fun buildRoomDB(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                UserDb::class.java,
                "userDb"
            ).build()
    }
}