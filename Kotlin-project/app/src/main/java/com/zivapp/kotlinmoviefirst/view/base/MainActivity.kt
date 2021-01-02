package com.zivapp.kotlinmoviefirst.view.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zivapp.kotlinmoviefirst.R
import com.zivapp.kotlinmoviefirst.view.favorite.FavoriteFragment
import com.zivapp.kotlinmoviefirst.view.search.SearchFragment
import com.zivapp.kotlinmoviefirst.view.home.HomeFragment

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private val home = HomeFragment()
    private val search = SearchFragment()
    private val favorite = FavoriteFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(home)
        navigation()
    }

    private fun loadFragment(fragment: Fragment) =
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, fragment)
                commit()
            }

    private fun navigation() {
        val navigation = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_movies -> loadFragment(home)
                R.id.nav_search -> loadFragment(search)
                R.id.nav_favorites -> loadFragment(favorite)
            }
            true
        }
    }
}