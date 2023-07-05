package com.mfdsix.astedroid.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mfdsix.astedroid.core.domain.model.Asteroid
import com.mfdsix.astedroid.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailAsteroid = intent.getParcelableExtra<Asteroid>(EXTRA_DATA)
        showDetail(detailAsteroid)
    }

    private fun showDetail(detaiAsteroid: Asteroid?){}

    private fun toogleStatusFavorite(statusFavorite: Boolean) {
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}