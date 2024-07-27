package com.example.data_profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.data_profile.databinding.CardItemBinding
import com.example.data_profile.room.profile

class MyRecyclerViewAdapter(private var profileList: List<profile> , private val clickListner:(profile)->Unit) : RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>(){

    var dummy_profile:profile = profile(0,"sk","28","financier","xxx")


    class MyViewHolder(val binding:CardItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(profile: profile , clickListner: (profile) -> Unit){
            binding.nameTextView.text = profile.name
            binding.ageTextView.text = profile.age
            binding.skillTextView.text = profile.skill
            binding.imageTextView.text = profile.image

            binding.listItemLayout.setOnClickListener{
                clickListner(profile)
            }
        }

    }
    fun updateList(newList: List<profile>) {
        profileList = newList
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        var layoutInflater = LayoutInflater.from(parent.context)
        val binding:CardItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.card_item,parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {

        return profileList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(profileList[position],clickListner)
    }
}