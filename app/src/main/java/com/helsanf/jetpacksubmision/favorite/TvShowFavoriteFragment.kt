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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.helsanf.jetpacksubmision.R
import com.helsanf.jetpacksubmision.adapter.TvShowAdapter
import com.helsanf.jetpacksubmision.adapter.TvShowPagedListAdapter
import com.helsanf.jetpacksubmision.detail.DetailActivity
import com.helsanf.jetpacksubmision.di.Injection
import com.helsanf.jetpacksubmision.model.modelrespone.movie.ResultMovie
import com.helsanf.jetpacksubmision.model.modelrespone.movie.tvshow.ResultTvShow
import com.helsanf.jetpacksubmision.tvshow.TvShowViewModel
import kotlinx.android.synthetic.main.fragment_tv_show_favorite.*

/**
 * A simple [Fragment] subclass.
 */
class TvShowFavoriteFragment : Fragment() {
    private var adapter: TvShowAdapter? = null
    private var viewModel: TvShowViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_tv_show_favorite, container, false)
        val progressTvShowFavorite : ProgressBar = view.findViewById(R.id.progressTvShowFavorite)
        progressTvShowFavorite.visibility = View.VISIBLE
        viewModel = obtainViewModel()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel!!.favoritesTvShow?.observe(this, Observer{
            progressTvShowFavorite.visibility = View.GONE

            adapter =
                TvShowAdapter(requireContext(), it, { item: ResultTvShow -> getItemClick(item) })
            rv_tvshow_favorite.adapter = adapter
            rv_tvshow_favorite.setHasFixedSize(true)
            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
            rv_tvshow_favorite.layoutManager = layoutManager
            adapter!!.notifyDataSetChanged()


//            adapter =
//                TvShowPagedListAdapter({ item: ResultTvShow -> getItemClick(item) })
//            adapter?.submitList(it)
//            rv_tvshow_favorite.adapter = adapter
//            rv_tvshow_favorite.setHasFixedSize(true)
//            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.activity)
//            rv_tvshow_favorite.layoutManager = layoutManager
//            adapter!!.notifyDataSetChanged()
        })
    }

    private fun obtainViewModel(): TvShowViewModel {
        return ViewModelProviders.of(this, Injection().getMovieRepo(requireContext()))
            .get(TvShowViewModel::class.java)
    }
    fun getItemClick(item: ResultTvShow) {
        val intent = Intent(activity, DetailActivity::class.java)
        Toast.makeText(activity,item.original_name,Toast.LENGTH_SHORT).show()
        intent.putExtra("tv_id", item.id)
        intent.putExtra("fromfavorites",true)
        startActivity(intent)
    }


}
