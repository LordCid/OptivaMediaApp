package com.example.omapp.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.omapp.R
import com.example.omapp.common.ImagesLoader
import com.example.omapp.common.formatDuration
import com.example.omapp.databinding.ItemMovieBinding
import com.example.omapp.domain.model.Movie

class MovieAdapter(
    private val imagesLoader: ImagesLoader
) : PagingDataAdapter<Movie, MovieAdapter.MovieViewHolder>(TaskDiffCallBack()) {

    var onCLick: (String) -> Unit = {}

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
        return MovieViewHolder(bindingView, imagesLoader, onCLick)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }

    }

    class MovieViewHolder(
        private val bindingView: ItemMovieBinding,
        private val imagesLoader: ImagesLoader,
        private val onCLick: (String) -> Unit
    ) : RecyclerView.ViewHolder(bindingView.root) {

        fun bind(item: Movie) {
            bindingView.apply {
//                root.setOnClickListener { onCLick(item.id) }
                imagesLoader.loadImage(item.imagesURL.first(), movieContainer)
                titleTv.text = item.name
                yearTv.text = item.year.toString()
                durationTv.text = itemView.context.getString(R.string.duration, item.duration.formatDuration())
            }
        }
    }
}