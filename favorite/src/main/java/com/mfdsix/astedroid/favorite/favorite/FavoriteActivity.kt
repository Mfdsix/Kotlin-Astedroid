package com.mfdsix.astedroid.favorite.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfdsix.astedroid.R
import com.mfdsix.astedroid.core.ui.FavoritedAsteroidAdapter
import com.mfdsix.astedroid.detail.DetailActivity
import com.mfdsix.astedroid.di.MapsModuleDependencies
import com.mfdsix.astedroid.favorite.ViewModelFactory
import com.mfdsix.astedroid.favorite.di.DaggerMapsComponent
import com.mfdsix.astedroidfavorite.databinding.ActivityFavoriteBinding
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ViewModelFactory
    private val favoriteViewModel: FavoriteViewModel by viewModels{
        factory
    }
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var asteroidAdapter: FavoritedAsteroidAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerMapsComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    MapsModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)

        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar.toolbar)
        supportActionBar?.title = resources.getString(R.string.favorite_asteroid)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        initializeViewModel()
    }

    private fun initializeRecyclerView(){
        if(!this::asteroidAdapter.isInitialized) {
            asteroidAdapter = FavoritedAsteroidAdapter()
            asteroidAdapter.onItemClick = { selectedData ->
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra(DetailActivity.ASTEROID_DATA, selectedData)
                startActivity(intent)
            }

            with(binding.rvAsteroid) {
                layoutManager = LinearLayoutManager(this@FavoriteActivity)
                setHasFixedSize(true)
                adapter = asteroidAdapter
            }
        }
    }

    private fun initializeViewModel(){
        favoriteViewModel.asteroid.observe(this) { asteroid ->
            if (asteroid != null && asteroid.isNotEmpty()) {
                initializeRecyclerView()
                asteroidAdapter.submitList(asteroid)
            }

            with(binding){
                rvAsteroid.visibility = if(asteroid != null && asteroid.isNotEmpty()) VISIBLE else GONE
                viewEmpty.root.visibility = if(asteroid != null && asteroid.isNotEmpty()) GONE else VISIBLE
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    companion object {
        const val VISIBLE = View.VISIBLE
        const val GONE = View.GONE
    }
}