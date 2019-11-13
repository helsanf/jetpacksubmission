package com.helsanf.jetpacksubmision.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.helsanf.jetpacksubmision.movie.MovieFragment
import com.helsanf.jetpacksubmision.tvshow.TvshowFragment


class MainPagerAdapter(fragment: FragmentManager) : FragmentPagerAdapter(fragment) {

    private val pages = listOf(
        MovieFragment(),
        TvshowFragment()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Movie"
            else -> "Tv Show"
        }
    }
}