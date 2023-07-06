package com.mfdsix.astedroid.home

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfdsix.astedroid.R
import com.mfdsix.astedroid.core.data.Resource
import com.mfdsix.astedroid.core.ui.AsteroidAdapter
import com.mfdsix.astedroid.databinding.ActivityHomeBinding
import com.mfdsix.astedroid.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var binding: ActivityHomeBinding
    private lateinit var asteroidAdapter: AsteroidAdapter

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = resources.getString(R.string.app_name)
        initializeRecyclerView()
        initializeViewModel()
    }

    private fun initializeRecyclerView(){
        asteroidAdapter = AsteroidAdapter()
        asteroidAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.ASTEROID_DATA, selectedData)
            startActivity(intent)
        }

        with(binding.rvAsteroid) {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            setHasFixedSize(true)
            adapter = asteroidAdapter
        }
    }

    private fun initializeViewModel(){
        homeViewModel.asteroid.observe(this) { asteroid ->
            if (asteroid != null) {
                when (asteroid) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        asteroidAdapter.setData(asteroid.data)
                    }

                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text =
                            asteroid.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        }
    }

    private fun goToFavorite(){
        val uri = Uri.parse("astedroid://favorite")
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }
}