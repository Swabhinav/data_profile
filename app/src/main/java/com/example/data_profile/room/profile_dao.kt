package com.example.data_profile.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface profile_dao {


    @Insert
    suspend fun insert(profile: profile):Long

    @Update
    suspend fun update(profile: profile)

    @Delete
    suspend fun delete(profile: profile)

    @Query("select * from profile_table")
    fun getallprofile():LiveData<List<profile>>



}