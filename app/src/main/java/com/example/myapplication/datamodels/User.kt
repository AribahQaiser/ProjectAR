package com.example.myapplication.datamodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "username")
    val username: String,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "password")
    val password: String, // In a real app, hash this!

    @ColumnInfo(name = "address")
    val address: String,

    @ColumnInfo(name = "phone_number")
    val phoneNumber: String
)