package com.example.moviecatalogue.favorite.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.DetailActivity
import com.example.moviecatalogue.R
import com.example.moviecatalogue.core.adapter.MovieViewAdapter
import com.example.moviecatalogue.core.domain.model.Movie
import com.example.moviecatalogue.databinding.FragmentMovieBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {
   private val movieFavoriteViewModel: com.example.moviecatalogue.favorite.viewmodel.FavoriteViewModel by viewModel()
    private var typee = ""
    private val movieViewAdapter = MovieViewAdapter()

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"
        private const val PAGE = "page"
    }

    private var binding: FragmentMovieBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val index = arguments?.getInt(ARG_SECTION_NUMBER, 0)
        val page = arguments?.getString(PAGE)

        binding?.apply {
            pbLoading.visibility = View.VISIBLE
            rvMovie.layoutManager = LinearLayoutManager(requireActivity())
            rvMovie.adapter = movieViewAdapter
        }
        if (index == 1) {
            if (page == getString(R.string.fav)) {
                getFavMovie(getString(R.string.movie))
                typee = getString(R.string.movie)
            }

        } else if (index == 2) {
           if (page == getString(R.string.fav)) {
                getFavMovie(getString(R.string.tv))
                typee = getString(R.string.tv)
            }

        }


        movieViewAdapter.setOnItemClickCallback(object : MovieViewAdapter.OnItemClickCallback {
            override fun onItemClick(data: Movie) {
                val intent = Intent(requireActivity(), DetailActivity::class.java).apply {
                    putExtra(getString(R.string.id), data.id)
                    putExtra(getString(R.string.type), typee)
                }
                startActivity(intent)
            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


    private fun getFavMovie(type1: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val movie = movieFavoriteViewModel.getDataMovie(type1)
            withContext(Dispatchers.Main){
                movie.observe(requireActivity(), {
                    movieViewAdapter.setData(it as ArrayList<Movie>)
                    if (it.isNotEmpty()) {
                    binding?.apply {
                        rvMovie.layoutManager = LinearLayoutManager(requireActivity())
                        rvMovie.adapter = movieViewAdapter
                    }
                } else {
                    Toast.makeText(
                            requireActivity(),
                            getString(R.string.data_not_exist),
                            Toast.LENGTH_SHORT
                    ).show()
                }
                binding?.pbLoading?.visibility = View.GONE
            })
            }
        }
    }

}