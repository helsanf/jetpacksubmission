package com.helsanf.jetpacksubmision.favorite


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.helsanf.jetpacksubmision.R
import com.helsanf.jetpacksubmision.adapter.FavoritesPagerAdapter
import kotlinx.android.synthetic.main.fragment_favorite.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteMenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(activity as AppCompatActivity){
            view_pager_favorites.adapter = FavoritesPagerAdapter(childFragmentManager)
            tab_layout_favorites.setupWithViewPager(view_pager_favorites)
        }
    }


}
