package com.example.omapp.presentation

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.omapp.common.ImagesLoader
import com.example.omapp.common.presentation.BaseFragment
import com.example.omapp.databinding.FragmentMovieListBinding
import com.example.omapp.domain.model.Movie
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieListFragment : BaseFragment() {

    private var binding: FragmentMovieListBinding? = null

    private val viewModel: MovieListViewModel by viewModel()

    private val imagesLoader : ImagesLoader by inject()

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        setViewModel()

    }

    private fun initUI(){
        movieAdapter = MovieAdapter(
            imagesLoader = imagesLoader
        )
        binding?.listView?.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = movieAdapter
        }
        //            buttonFirst.setOnClickListener {
//                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//            }
    }

    private fun setViewModel(){
        viewModel.viewState.observe(viewLifecycleOwner, ::updateUI)
        viewModel.getMovies(0)
    }

    private fun updateUI(viewState: MovieListViewState) {
        when(viewState) {
            MovieListViewState.Error -> showError()
            MovieListViewState.Loading -> showLoadingDialogFragment()
            is MovieListViewState.ShowMovies -> showMovies(viewState.data)
        }
    }

    private fun showMovies(data: List<Movie>) {
        hideLoadingDialogFragment()
//        binding?.apply {
//            listView.isVisible = true
//            errorView.isVisible = false
//        }
        movieAdapter.submitList(data)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


    private fun showError() {
        hideLoadingDialogFragment()
        binding?.apply {
            listView.isVisible = false
            errorView.isVisible = true
        }
    }
}