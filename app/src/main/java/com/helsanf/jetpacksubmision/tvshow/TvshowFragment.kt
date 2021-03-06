package com.helsanf.jetpacksubmision.tvshow


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.helsanf.jetpacksubmision.R
import com.helsanf.jetpacksubmision.adapter.TvShowAdapter
import com.helsanf.jetpacksubmision.detail.DetailActivity
import com.helsanf.jetpacksubmision.di.Injection
import com.helsanf.jetpacksubmision.model.TvShow
import com.helsanf.jetpacksubmision.model.modelrespone.movie.tvshow.ResultTvShow
import kotlinx.android.synthetic.main.fragment_tvshow.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class TvshowFragment : Fragment() {

    private var adapter: TvShowAdapter? = null
    private var item: MutableList<TvShow> = mutableListOf()
    private var viewModel : TvShowViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        progressTV.visibility = View.VISIBLE
        viewModel = obtainViewModel()
        initView()
//        viewModel!!.tvShow.observe(this,Observer{
//
//        })
//        item.clear()
//        item.addAll(viewModel!!.getTvShow())

    }
    private fun initView() = GlobalScope.launch(Dispatchers.Main) {
        val tvGet = viewModel!!.tvShow.await()
        tvGet.observe(this@TvshowFragment, Observer {
            progressTV.visibility = View.GONE
            adapter =
                TvShowAdapter(requireContext(), it, { item: ResultTvShow -> getItemClick(item) })
            rv_tvshow.adapter = adapter
            rv_tvshow.setHasFixedSize(true)
            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
            rv_tvshow.layoutManager = layoutManager
            adapter!!.notifyDataSetChanged()
        })
    }
    fun getItemClick(item: ResultTvShow) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("tv_id", item.id)
        Log.e("ID",item.id.toString())
        startActivity(intent)
    }
    private fun obtainViewModel(): TvShowViewModel {
        return ViewModelProviders.of(this, Injection().getMovieRepo(requireContext())).get(TvShowViewModel::class.java)
    }

}
