package com.helsanf.jetpacksubmision.movie


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.helsanf.jetpacksubmision.R
import com.helsanf.jetpacksubmision.adapter.MovieAdapter
import com.helsanf.jetpacksubmision.detail.DetailActivity
import com.helsanf.jetpacksubmision.model.Movie
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {

    private var adapter: MovieAdapter? = null
    private var item: MutableList<Movie> = mutableListOf()
    private var viewModel : MovieViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =ViewModelProviders.of(this).get(MovieViewModel::class.java)
        item.clear()
        item.addAll(viewModel!!.getMovie())
        adapter =
            MovieAdapter(this.activity!!, item, { item: Movie -> getItemClick(item) })
        rv_movie.adapter = adapter
        rv_movie.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.activity)
        rv_movie.layoutManager = layoutManager
        adapter!!.notifyDataSetChanged()
    }

    fun getItemClick(item: Movie) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("movie_id", item.movieId)
        startActivity(intent)
    }
}
