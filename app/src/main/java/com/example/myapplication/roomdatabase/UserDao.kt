package com.example.myapplication.roomdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.datamodels.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<User>>

    @Query("SELECT * FROM users WHERE email = :username")
    suspend fun getUserByUsername(username: String): User?
}