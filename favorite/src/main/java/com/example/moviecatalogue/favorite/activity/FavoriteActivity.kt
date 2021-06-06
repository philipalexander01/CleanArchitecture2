package com.example.moviecatalogue.favorite.activity

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.example.moviecatalogue.R
import com.example.moviecatalogue.core.adapter.TabViewAdapter
import com.example.moviecatalogue.favorite.databinding.ActivityFavoriteBinding
import com.example.moviecatalogue.favorite.di.favoriteModule
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class FavoriteActivity : AppCompatActivity() {
    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_movie,
            R.string.tab_tv
        )
    }
    private var binding: ActivityFavoriteBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportActionBar?.title = this.getString(R.string.fav)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        loadKoinModules(favoriteModule)
        val tabViewAdapter = TabViewAdapter(this,getString(R.string.fav))
        binding?.apply {
            viewPager.adapter = tabViewAdapter
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
            supportActionBar?.elevation = 0f
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(favoriteModule)
        binding = null
    }

}