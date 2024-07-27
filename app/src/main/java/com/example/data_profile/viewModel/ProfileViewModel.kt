package com.example.data_profile.viewModel

import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data_profile.repository.ProfileRepository
import com.example.data_profile.room.profile
import kotlinx.coroutines.launch

class ProfileViewModel(private var repository: ProfileRepository):ViewModel(), Observable {


    val profiles = repository.profiles
    private var isUpdateOrDelete = false
    private lateinit var profileToUpdateorDelete:profile


    //DataBinding with Live Data
    @Bindable
    var inputName = MutableLiveData<String?>()

    @Bindable
    var inputAge = MutableLiveData<String?>()

    @Bindable
    var inputSkill = MutableLiveData<String?>()

    @Bindable
    var inputImage = MutableLiveData<String?>()

    //Button
    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String?>()
    @Bindable
    val clearAllOrDeleteText = MutableLiveData<String?>()

    fun covertInttoString(x:Int):String{
        return x.toString()
    }

    init {

        saveOrUpdateButtonText.value ="Save"
        clearAllOrDeleteText.value = "Clear All"

    }

    fun insert(profile: profile) = viewModelScope.launch {
        repository.insert(profile)
    }

    fun delete(profile: profile) = viewModelScope.launch {
        repository.delete(profile)

        inputName.value = null
        inputAge.value =null
        inputSkill.value = null
        inputImage.value = null
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteText.value= "Clear All"
    }

    fun update(profile: profile)= viewModelScope.launch {
        repository.update(profile)

        inputName.value = null
        inputAge.value =null
        inputSkill.value = null
        inputImage.value = null
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteText.value= "Clear All"


    }

    fun saveOrUpdate(){
        if (isUpdateOrDelete){
            //make an Update
            profileToUpdateorDelete.name = inputName.value!!
            profileToUpdateorDelete.age = inputAge.value!!
            profileToUpdateorDelete.skill = inputSkill.value!!
            profileToUpdateorDelete.image = inputImage.value!!
        }else{
            val name = inputName.value!!
            val age = inputAge.value!!
            val skill = inputSkill.value!!
            val image = inputImage.value!!

            insert(profile(0,name,age,skill,image))

            inputName.value = null
            inputAge.value = null
            inputSkill.value = null
            inputImage.value = null
        }



    }

    fun deleteOrClear() {
        if (isUpdateOrDelete) {
            delete(profileToUpdateorDelete)
        }else{
            Log.d("Profile Recycler View", "Clear all ")
        }
    }

    fun initUpdateAndDelete(profile: profile){
        inputName.value = profile.name
        inputAge.value = profile.age
        inputSkill.value = profile.skill
        inputImage.value = profile.image
        isUpdateOrDelete = true
        profileToUpdateorDelete = profile
        saveOrUpdateButtonText.value = "Update"


    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }


}