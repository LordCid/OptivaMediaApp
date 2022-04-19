package com.example.omapp.presentation

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.omapp.common.ImagesLoader
import com.example.omapp.databinding.FragmentMovieListBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MovieListFragment : Fragment() {

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
        binding?.apply {
//            buttonFirst.setOnClickListener {
//                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//            }
        }
    }

    private fun initUI(){
        movieAdapter = MovieAdapter(
            imagesLoader = imagesLoader,
            dateFormat = DateFormat.getDateFormat(activity)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}