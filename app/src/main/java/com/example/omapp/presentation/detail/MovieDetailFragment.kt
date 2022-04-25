package com.example.omapp.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.omapp.R
import com.example.omapp.common.ImagesLoader
import com.example.omapp.common.formatDuration
import com.example.omapp.common.presentation.BaseFragment
import com.example.omapp.databinding.FragmentMovieDetailBinding
import com.example.omapp.domain.model.Movie
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment : BaseFragment() {

    private var binding: FragmentMovieDetailBinding? = null

    private val viewModel: MovieDetailViewModel by viewModel()

    private val imagesLoader: ImagesLoader by inject()

    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
        setOnClicks()
    }

    private fun setViewModel() {
        viewModel.viewState.observe(viewLifecycleOwner, ::updateUI)
        viewModel.getMovieDetail(args.id)
    }

    private fun setOnClicks() {
        binding?.apply {
            favouriteButton.setOnClickListener { TODO()  }
        }
    }

    private fun updateUI(viewState: MovieDetailViewState) {
        when (viewState) {
            is MovieDetailViewState.Error -> showErrorMessage(viewState.message)
            MovieDetailViewState.Loading -> showLoadingDialogFragment()
            is MovieDetailViewState.ShowMovies -> showData(viewState.data)
        }
    }

    private fun showData(data: Movie) {
        binding?.apply {
            with(header) {
                imagesLoader.loadImage(data.imagesURL.first(), movieContainer)
                titleTv.text = data.name
                yearTv.text = data.year.toString()
                durationTv.text =
                    context?.getString(R.string.duration, data.duration.formatDuration())
            }
            descriptionTv.text = data.description
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}