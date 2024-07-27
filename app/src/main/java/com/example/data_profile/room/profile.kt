package com.example.data_profile.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "profile_table")
data class profile(

    @PrimaryKey(autoGenerate = true)
    var id: Int,


    var name: String,

    var age: String,


    var skill: String,


    var image: String
)
