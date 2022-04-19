package com.example.omapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.omapp.common.ImagesLoader
import com.example.omapp.databinding.ItemMovieBinding
import com.example.omapp.domain.model.Movie
import java.text.DateFormat

class MovieAdapter(
    private val imagesLoader: ImagesLoader,
    private val dateFormat: DateFormat
) : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(TaskDiffCallBack()) {

    var onCLick: (Long) -> Unit = {}

    class TaskDiffCallBack : DiffUtil.ItemCallback<Movie>() {

        override fun areItemsTheSame(
            oldItem: Movie,
            newItem: Movie
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Movie,
            newItem: Movie
        ) = oldItem == newItem
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val bindingView = ItemMovieBinding.inflate(inflater, parent, false)
        return MovieViewHolder(bindingView, imagesLoader, dateFormat, onCLick)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MovieViewHolder(
        private val bindingView: ItemMovieBinding,
        private val imagesLoader: ImagesLoader,
        private val dateFormat: DateFormat,
        private val onCLick: (Long) -> Unit
    ) : RecyclerView.ViewHolder(bindingView.root) {

        fun bind(item: Movie) {
            bindingView.apply {
                root.setOnClickListener { onCLick(item.id) }
//                imagesLoader.loadImage(group.defaultImageUrl, group_container)
                titleTv.text = item.name
                dateTv.text = item.year.toString()
            }
        }
    }
}