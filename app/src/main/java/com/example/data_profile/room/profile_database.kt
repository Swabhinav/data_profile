package com.example.data_profile.room

import androidx.room.Database
import androidx.room.Room
import android.content.Context
import androidx.room.RoomDatabase


@Database(entities = [profile::class], version = 2)
abstract class profile_database : RoomDatabase() {

    abstract val profileDao: profile_dao


    companion object {

        @Volatile
        private var INSTANCE: profile_database? = null

        fun getInstance(context: Context): profile_database {
            var instance = INSTANCE
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    profile_database::class.java,
                    "profile_db"
                ).fallbackToDestructiveMigration()
                    .build()
            }
            INSTANCE = instance
            return instance
        }


    }

}