package com.helsanf.jetpacksubmision.movie


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.helsanf.jetpacksubmision.R
import com.helsanf.jetpacksubmision.ViewModelFactory
import com.helsanf.jetpacksubmision.adapter.MovieAdapter
import com.helsanf.jetpacksubmision.detail.DetailActivity
import com.helsanf.jetpacksubmision.di.Injection
import com.helsanf.jetpacksubmision.model.modelrespone.movie.ResultMovie
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {

    private var adapter: MovieAdapter? = null
    private var viewModel: MovieViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        progressMovie.visibility = View.VISIBLE
        viewModel = obtainViewModel()
        viewModel!!.getAllMovie().observe(this, Observer{
            progressMovie.visibility = View.GONE
            adapter =
                MovieAdapter(this.activity!!, it, { item: ResultMovie -> getItemClick(item) })
            rv_movie.adapter = adapter
            rv_movie.setHasFixedSize(true)
            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.activity)
            rv_movie.layoutManager = layoutManager
            adapter!!.notifyDataSetChanged()
        })

    }

    private fun obtainViewModel(): MovieViewModel {
        return ViewModelProviders.of(this, Injection().getMovieRepo(requireContext())).get(MovieViewModel::class.java)
    }

    fun getItemClick(item: ResultMovie) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("movie_id", item.id)
        startActivity(intent)
    }
}
