package com.helsanf.jetpacksubmision.movie


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.helsanf.jetpacksubmision.R
import com.helsanf.jetpacksubmision.adapter.MovieAdapter
import com.helsanf.jetpacksubmision.detail.DetailActivity
import com.helsanf.jetpacksubmision.di.Injection
import com.helsanf.jetpacksubmision.model.modelrespone.movie.ResultMovie
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.coroutines.*

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {

    private var adapter: MovieAdapter? = null
    private var viewModel: MovieViewModel? = null
    private val uiScope = CoroutineScope(Dispatchers.Main + Job())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_movie, container, false)
        // Inflate the layout for this fragment

        val progressMovie: ProgressBar = view.findViewById(R.id.progressMovie)
        progressMovie.visibility = View.VISIBLE
        viewModel = obtainViewModel()

        return view

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        insertAlltoLocal()
//                initUi()


    }

    private fun insertAlltoLocal() = uiScope.launch(Dispatchers.Main) {

        awaitAll(async {
            viewModel?.movieFromApi?.await()?.observe(this@MovieFragment, Observer {
                viewModel?.insertAllAsync(it)

                Log.e("RESULT",it.toString())
            })

        },async {
            initUi()
        })

    }


    private fun initUi() = uiScope.launch(Dispatchers.Main) {
        //        Log.e("TEST", "HELSAN_2")

        viewModel?.favoritesMovie?.observe(this@MovieFragment, Observer {
            if(it != null){
                progressMovie.visibility = View.GONE
                rv_movie.visibility = View.VISIBLE
                adapter =
                    MovieAdapter(requireContext(), it, { item: ResultMovie -> getItemClick(item) })
                rv_movie.adapter = adapter
                rv_movie.setHasFixedSize(true)
                val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
                rv_movie.layoutManager = layoutManager
                adapter!!.notifyDataSetChanged()
            }


        })
    }


    private fun obtainViewModel(): MovieViewModel {
        return ViewModelProviders.of(this, Injection().getMovieRepo(requireContext()))
            .get(MovieViewModel::class.java)
    }

    fun getItemClick(item: ResultMovie) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("movie_id", item.id)
        startActivity(intent)
    }
}
