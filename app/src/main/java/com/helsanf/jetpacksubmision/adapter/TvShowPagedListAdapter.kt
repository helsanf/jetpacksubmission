package com.helsanf.jetpacksubmision.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.helsanf.jetpacksubmision.R
import com.helsanf.jetpacksubmision.model.modelrespone.movie.tvshow.ResultTvShow
import com.squareup.picasso.Picasso

class TvShowPagedListAdapter(
                            private val listener: (ResultTvShow) -> Unit
) : PagedListAdapter<ResultTvShow,TvShowPagedListAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(viewgroup: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewgroup.context).inflate(
                R.layout.item_list,
                viewgroup,
                false
            )
        )
    }
    companion object{
        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<ResultTvShow>(){
            override fun areContentsTheSame(oldMovie: ResultTvShow, newMovie: ResultTvShow): Boolean {
                return oldMovie.id == newMovie.id
            }

            override fun areItemsTheSame(oldMovie: ResultTvShow, newMovie: ResultTvShow): Boolean {
                return oldMovie == newMovie
            }
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(getItem(position), listener)

    }

    class ViewHolder(itemsView: View) : RecyclerView.ViewHolder(itemsView) {
        val tvTitle : TextView = itemsView.findViewById(R.id.tv_item_title)
        val imgPoster : ImageView = itemsView.findViewById(R.id.img_poster)
        val tvDescription : TextView = itemsView.findViewById(R.id.tv_item_description)
        val tvDate: TextView = itemsView.findViewById(R.id.tv_item_date)
        fun bindItem(items: ResultTvShow?, listener: (ResultTvShow) -> Unit) {
            items?.poster_path.let { Picasso.get().load("https://image.tmdb.org/t/p/w185/$it").into(imgPoster) }
            tvTitle.text = items?.original_name
            tvDescription.text = items?.overview
            tvDate.text = items?.first_air_date
            itemView.setOnClickListener { items?.let { it1 -> listener(it1) } }
        }
    }
}