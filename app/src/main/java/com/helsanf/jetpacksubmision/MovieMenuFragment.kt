package com.helsanf.jetpacksubmision


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.helsanf.jetpacksubmision.adapter.MainPagerAdapter
import kotlinx.android.synthetic.main.fragment_movie_menu.*

/**
 * A simple [Fragment] subclass.
 */
class MovieMenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(activity as AppCompatActivity){
            view_pager_movie.adapter = MainPagerAdapter(childFragmentManager)
            tab_layout_movie.setupWithViewPager(view_pager_movie)
        }
    }
}
