package com.helsanf.jetpacksubmision.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.helsanf.jetpacksubmision.R
import com.helsanf.jetpacksubmision.model.Movie
import com.squareup.picasso.Picasso

class MovieAdapter(private val context: Context,
                   private val items: List<Movie>,
                   private val listener: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewgroup: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_list,
                viewgroup,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)

    }

    class ViewHolder(itemsView: View) : RecyclerView.ViewHolder(itemsView) {
        val tvTitle : TextView = itemsView.findViewById(R.id.tv_item_title)
        val imgPoster : ImageView = itemsView.findViewById(R.id.img_poster)
        val tvDescription : TextView = itemsView.findViewById(R.id.tv_item_description)
        val tvDate: TextView = itemsView.findViewById(R.id.tv_item_date)
        fun bindItem(items: Movie, listener: (Movie) -> Unit) {
            items.poster_path?.let { Picasso.get().load(it).into(imgPoster) }
            tvTitle.text = items.title
            tvDescription.text = items.overview
            tvDate.text = items.release_date
            itemView.setOnClickListener { listener(items) }
        }
    }
}