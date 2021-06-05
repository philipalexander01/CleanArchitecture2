package com.example.moviecatalogue.core.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabViewAdapter(activity: AppCompatActivity, private val page:String) : FragmentStateAdapter(activity) {
    override fun createFragment(position: Int): Fragment {
        var fragment:Fragment? = null
        fragment = if(page == "home"){
            Class.forName("com.example.moviecatalogue.fragment.MovieFragment").newInstance() as Fragment
        }else{
            Class.forName("com.example.moviecatalogue.favorite.fragment.MovieFragment").newInstance() as Fragment
        }
        fragment.arguments = Bundle().apply {
            putInt("section_number", position+1)
            putString("page", page)
        }
       return fragment
    }


    override fun getItemCount(): Int {
        return 2
    }


}