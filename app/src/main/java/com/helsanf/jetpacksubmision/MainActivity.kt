package com.helsanf.jetpacksubmision

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.helsanf.jetpacksubmision.favorite.FavoriteMenuFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//            view_pager_main.adapter = MainPagerAdapter(supportFragmentManager)
//            tab_layout_main.setupWithViewPager(view_pager_main)

        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.movie -> {
                    loadMovieFragment(savedInstanceState)
                }
                R.id.favorites ->{
                    loadFavoriteFragment(savedInstanceState)
                }
            }
            true
        }
        bottomNav.selectedItemId = R.id.movie
    }

    private fun loadMovieFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.mainContainer,
                    MovieMenuFragment(), MovieMenuFragment::class.java.simpleName
                )
                .commit()
        }
    }

    private fun loadFavoriteFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.mainContainer,
                    FavoriteMenuFragment(), FavoriteMenuFragment::class.java.simpleName
                )
                .commit()
        }
    }
}
