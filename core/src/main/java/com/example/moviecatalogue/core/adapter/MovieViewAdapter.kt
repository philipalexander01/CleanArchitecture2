package com.example.moviecatalogue.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecatalogue.core.R
import com.example.moviecatalogue.core.databinding.ItemListBinding
import com.example.moviecatalogue.core.domain.model.Movie


class MovieViewAdapter : RecyclerView.Adapter<MovieViewAdapter.ViewHolder>() {
    private val data = ArrayList<Movie>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataMovie = data[position]
        holder.binding.apply {
            ivPoster.loadImage(dataMovie.poster_path)
             tvTitle.text = dataMovie.title
            tvOverview.text = dataMovie.overview
            materialCardView.setOnClickListener {
                onItemClickCallback.onItemClick(dataMovie)
            }
        }
    }

    override fun getItemCount(): Int = data.size
    fun setData(data: ArrayList<Movie>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    private fun ImageView.loadImage(url: String) {
        Glide.with(this.context).load(context.getString(R.string.url_img) + url)
            .into(this)
    }

    interface OnItemClickCallback {
        fun onItemClick(data: Movie)
    }
}