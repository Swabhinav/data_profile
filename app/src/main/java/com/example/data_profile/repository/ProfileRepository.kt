package com.example.data_profile.repository

import com.example.data_profile.room.profile
import com.example.data_profile.room.profile_dao

class ProfileRepository(private var profileDao:profile_dao) {


    val profiles = profileDao.getallprofile()

    suspend fun insert(profile: profile):Long{
        return profileDao.insert(profile)
    }

    suspend fun delete(profile: profile){
        return profileDao.delete(profile)
    }

    suspend fun update(profile: profile){
        return profileDao.update(profile)
    }

}