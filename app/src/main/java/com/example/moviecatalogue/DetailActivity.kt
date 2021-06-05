package com.example.moviecatalogue

import android.content.Intent
import android.graphics.Color
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.bumptech.glide.Glide
import com.example.moviecatalogue.core.R
import com.example.moviecatalogue.core.domain.model.Movie
import com.example.moviecatalogue.core.utils.Utility
import com.example.moviecatalogue.databinding.ActivityDetailBinding
import com.example.moviecatalogue.databinding.NoInternetLayoutBinding
import com.example.moviecatalogue.viewmodel.DetailViewModel
import com.example.moviecatalogue.viewmodel.MovieViewModel
import com.example.moviecatalogue.viewmodel.TvViewModel
import es.dmoral.toasty.Toasty
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private val movieViewModel: MovieViewModel by viewModel()
    private val tvViewModel: TvViewModel by viewModel()
    private val detailMovieViewModel: DetailViewModel by viewModel()

    private var binding: ActivityDetailBinding? = null
    private var binding1: NoInternetLayoutBinding? = null
    private var actNw: NetworkCapabilities? = null
    private var from: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val data: ArrayList<Movie> = ArrayList()

        val id = intent.getIntExtra(this.getString(R.string.id), 0)
        val type = intent.getStringExtra(this.getString(R.string.type))
        binding?.ivBack?.setOnClickListener {
            onBackPressed()
        }

        //check internet connection
        actNw = Utility().checkInternetConnection(this)
        //if there is no an internet connection
        if (actNw == null) {
            binding1 = NoInternetLayoutBinding.inflate(layoutInflater)
            setContentView(binding1?.root)

            binding1?.btnCobaLagi?.setOnClickListener {
                actNw = Utility().checkInternetConnection(this)
                if (actNw != null) {
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra(getString(R.string.id), id)
                    intent.putExtra(getString(R.string.type), type)
                    startActivity(intent)
                    finish()
                }
            }
        } else {
            binding?.pbLoading?.visibility = View.VISIBLE
            var isActive = false
            when (type) {
                getString(R.string.movie) -> {
                    movieViewModel.getDataMovieById(id).observe(this, {
                        if (it != null) {
                            from = getString(R.string.movie)
                            data.clear()
                            data.add(it)
                            setData(it)
                            detailMovieViewModel.getDataMovieById(it.id).observe(this, {
                                if (it != null) {
                                    isActive = true
                                    setStatus(isActive)
                                }
                            })
                        } else {
                            Toast.makeText(
                                this@DetailActivity,
                                getString(R.string.data_not_exist),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        binding?.pbLoading?.visibility = View.GONE
                    })
                }
                getString(R.string.tv) -> {
                    tvViewModel.getDataTvById(id).observe(this, {
                        if (it != null) {
                            from=getString(R.string.tv)
                            data.clear()
                            data.add(it)
                            setData(it)
                            detailMovieViewModel.getDataMovieById(it.id).observe(this, {
                                if (it != null) {
                                    isActive = true
                                    setStatus(isActive)
                                }
                            })
                        } else {
                            Toast.makeText(
                                this@DetailActivity,
                                getString(R.string.data_not_exist)
                                ,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        binding?.pbLoading?.visibility = View.GONE
                    })
                }
            }

            binding?.favButton?.setOnClickListener {
                //if already add to favorite
                if (isActive) {
                    isActive = false
                    setStatus(isActive)

                    //delete data
                    data[0].let { it1 -> detailMovieViewModel.delete(it1.id) }
                    Toasty.success(
                        this@DetailActivity,
                        getString(R.string.sukses),
                        Toast.LENGTH_SHORT,
                        true
                    ).show()
                } else {
                    isActive = true
                    setStatus(isActive)
                    //insert data to database
                    if(from == getString(R.string.movie)){
                        detailMovieViewModel.insert(
                           Movie(
                                id = data[0].id,
                                title = data[0].title,
                                overview = data[0].overview,
                                poster_path = data[0].poster_path,
                                vote_average = data[0].vote_average,
                                date = data[0].date,
                                type = getString(R.string.movie)
                            )
                        )
                    }else{
                        detailMovieViewModel.insert(
                            Movie(
                                id = data[0].id,
                                title = data[0].title,
                                overview = data[0].overview,
                                poster_path = data[0].poster_path,
                                vote_average = data[0].vote_average,
                                date = data[0].date,
                                type = getString(R.string.tv)
                            )
                        )
                    }

                    Toasty.success(
                        this@DetailActivity,
                        getString(R.string.sukses1),
                        Toast.LENGTH_SHORT,
                        true
                    ).show()
                }
            }
        }
    }

    private fun setData(data: Movie) {
        binding?.apply {
            Glide.with(this@DetailActivity)
                .load(this@DetailActivity.getString(R.string.url_img) + data.poster_path)
                .into(ivAvatar)
             tvTitle.text = data.title
            tvDate.text = data.date
            tvOverview.text = data.overview
            setProgressTo(data.vote_average)
        }
    }

    private fun setProgressTo(progress: Double) {
        binding?.apply {
            include.progressTv.text = "$progress%"
            include.circularDeterminativePb.progress = (progress * 10).toInt()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        binding1 = null
    }



    //change favorite icon
    private fun setStatus(isActive: Boolean) {
        val myFabSrc = ResourcesCompat.getDrawable(
            resources,
            R.drawable.ic_baseline_favorite_24,
            null
        )
        val illBeWhite = myFabSrc?.constantState?.newDrawable()
        if (isActive) {
            illBeWhite?.mutate()?.colorFilter =
                BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                    Color.RED,
                    BlendModeCompat.SRC_ATOP
                )
            binding?.favButton?.setImageDrawable(illBeWhite)
        } else {
            illBeWhite?.mutate()?.colorFilter =
                BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                    Color.WHITE,
                    BlendModeCompat.SRC_ATOP
                )
            binding?.favButton?.setImageDrawable(illBeWhite)
        }
    }
}