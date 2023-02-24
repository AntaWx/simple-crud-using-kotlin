package com.surya_yasa_antariksa.latihancrud.database.dao

import androidx.room.*
import com.surya_yasa_antariksa.latihancrud.database.entity.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE uid IN(:userIds)")
    fun getAllById(userIds: IntArray): List<User>

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)

    @Update
    fun update(user: User)
}