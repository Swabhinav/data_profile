package com.example.data_profile.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data_profile.repository.ProfileRepository

class ViewModelFactory(private val repository: ProfileRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ProfileViewModel::class.java)){
            return ProfileViewModel(repository) as T
        }else{
            throw IllegalArgumentException("Unkown View Model Class")

        }
    }

}