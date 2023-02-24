package com.surya_yasa_antariksa.latihancrud.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.surya_yasa_antariksa.latihancrud.database.dao.UserDao
import com.surya_yasa_antariksa.latihancrud.database.entity.User

@Database(
    entities = [User::class],
    version = 2
)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object{
        private var instance: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase{
            if(instance == null){
                instance = Room.databaseBuilder(context, UserDatabase::class.java, "user-database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }

            return instance!!
        }
    }
}