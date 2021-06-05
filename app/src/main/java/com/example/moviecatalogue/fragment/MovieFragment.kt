package com.example.moviecatalogue.fragment

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
import com.example.moviecatalogue.viewmodel.MovieViewModel
import com.example.moviecatalogue.viewmodel.TvViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {
   private val movieViewModel: MovieViewModel by viewModel()
   private val tvViewModel: TvViewModel by viewModel()


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
            if (page == getString(R.string.home)) {

                    getMovieData()

            }

        } else if (index == 2) {
            if (page == getString(R.string.home)) {
              getTvData()
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

    private fun getMovieData() {
        movieViewModel.getDataMovie().observe(requireActivity(), {
            if (it.isNotEmpty()) {
                movieViewAdapter.setData(it)
                typee = getString(R.string.movie)
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
    private fun getTvData(){

        tvViewModel.getDataTv().observe(requireActivity(), {
            if (it.isNotEmpty()) {
                movieViewAdapter.setData(it)
                typee = getString(R.string.tv)
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