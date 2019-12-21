package com.helsanf.jetpacksubmision.favorite


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.helsanf.jetpacksubmision.R
import com.helsanf.jetpacksubmision.adapter.MovieAdapter
import com.helsanf.jetpacksubmision.adapter.MoviePagedListAdapter
import com.helsanf.jetpacksubmision.detail.DetailActivity
import com.helsanf.jetpacksubmision.di.Injection
import com.helsanf.jetpacksubmision.model.modelrespone.movie.ResultMovie
import com.helsanf.jetpacksubmision.movie.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie_favorites.*

/**
 * A simple [Fragment] subclass.
 */
class MovieFavoritesFragment : Fragment() {
    private var adapter: MovieAdapter? = null
    private var viewModel: MovieViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_movie_favorites, container, false)
        val progressMovieFavorite : ProgressBar = view.findViewById(R.id.progressMovieFavorite)
        progressMovieFavorite.visibility = View.VISIBLE
        viewModel = obtainViewModel()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel?.movieFavoriteList?.
        viewModel!!.favoritesMovie?.observe(this, Observer<List<ResultMovie>>{

            progressMovieFavorite.visibility = View.GONE
            adapter =
                MovieAdapter(requireContext(), it, { item: ResultMovie -> getItemClick(item) })
            rv_movie_favorite.adapter = adapter
            rv_movie_favorite.setHasFixedSize(true)
            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
            rv_movie_favorite.layoutManager = layoutManager
            adapter!!.notifyDataSetChanged()

//            adapter =
//                MovieAdapter({requireContext(), it ,item: ResultMovie -> getItemClick(item) })
//            rv_movie_favorite.adapter = adapter
//            rv_movie_favorite.setHasFixedSize(true)
//            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.activity)
//            rv_movie_favorite.layoutManager = layoutManager
//            adapter!!.notifyDataSetChanged()
        })
    }
    private fun obtainViewModel(): MovieViewModel {
        return ViewModelProviders.of(this, Injection().getMovieRepo(requireContext()))
            .get(MovieViewModel::class.java)
    }
    fun getItemClick(item: ResultMovie) {
        val intent = Intent(activity, DetailActivity::class.java)
        Toast.makeText(activity,item.title, Toast.LENGTH_SHORT).show()

        intent.putExtra("movie_id", item.id)
        intent.putExtra("fromfavorites",true)
        startActivity(intent)
    }


}
