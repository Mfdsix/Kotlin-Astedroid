package com.mfdsix.astedroid.detail

import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.mfdsix.astedroid.R
import com.mfdsix.astedroid.core.domain.model.Asteroid
import com.mfdsix.astedroid.core.utils.withDateFormat
import com.mfdsix.astedroid.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()
    private var asteroid: Asteroid? = null
    private var isFavorited: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        asteroid = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(ASTEROID_DATA, Asteroid::class.java)
        } else {
            intent.getParcelableExtra(ASTEROID_DATA)
        }

        setSupportActionBar(binding.toolbar.toolbar)
        supportActionBar?.title = resources.getString(R.string.asteroid_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        showDetail()

        binding.imgLove.setOnClickListener {
            toggleStatusFavorite()
        }
    }

    private fun showDetail(){
        with(binding) {
            Glide.with(this@DetailActivity)
                .load(
                    asteroid!!.image ?: ContextCompat.getDrawable(
                        this@DetailActivity,
                        com.mfdsix.astedroid.core.R.drawable.asteroid
                    )
                )
                .into(imgAsteroid)
            tvTitle.text = asteroid!!.title
            tvDescription.text = asteroid!!.description
            tvDate.text = asteroid!!.createdAt.withDateFormat()
            tvCenter.text = asteroid!!.center

            getLocalAsteroid()
        }
    }

    private fun getLocalAsteroid(){
        detailViewModel.getDetail(asteroid!!.id).observe(this@DetailActivity){
            isFavorited = it != null && it.isFavorite
            showToggleFavoriteButton()
        }
    }

    private fun toggleStatusFavorite() {
        if(asteroid != null){
            detailViewModel.toggleFavorite(asteroid!!, !isFavorited)
            getLocalAsteroid()
        }
    }

    private fun showToggleFavoriteButton(){
        if (isFavorited) {
            binding.imgLove.setImageDrawable(
                ContextCompat.getDrawable(
                    this@DetailActivity,
                    R.drawable.love_active
                )
            )
        } else {
            binding.imgLove.setImageDrawable(
                ContextCompat.getDrawable(
                    this@DetailActivity,
                    R.drawable.love
                )
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    companion object {
        const val ASTEROID_DATA = "asteroid_data"
    }
}