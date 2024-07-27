package com.example.data_profile

import android.os.Bundle

import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data_profile.databinding.ActivityMainBinding
import com.example.data_profile.repository.ProfileRepository
import com.example.data_profile.room.profile
import com.example.data_profile.room.profile_database
import com.example.data_profile.viewModel.ProfileViewModel
import com.example.data_profile.viewModel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        //Room Database
        var dao = profile_database.getInstance(applicationContext).profileDao
        val repository = ProfileRepository(dao)
        val factory = ViewModelFactory(repository)


        //View Model
        profileViewModel = ViewModelProvider(
            this,factory
        ).get(ProfileViewModel::class.java)

        binding.profileView = profileViewModel

        binding.lifecycleOwner = this

        initRecyclerView()



    }

    private fun initRecyclerView(){
        binding.RecyclerView.layoutManager = LinearLayoutManager(this)
        binding.RecyclerView.adapter = MyRecyclerViewAdapter(emptyList(), {selectedItem:profile -> listItemClicked(selectedItem)})
        DisplayUserList()
    }

    private fun DisplayUserList(){
        profileViewModel.profiles.observe(this,{
            (binding.RecyclerView.adapter as MyRecyclerViewAdapter).updateList(it)
        })
    }

    private fun listItemClicked(selectedItem:profile){
        Toast.makeText(this,"Selected Name is ${selectedItem.name}",Toast.LENGTH_LONG).show()
        profileViewModel.initUpdateAndDelete(selectedItem)

    }
}