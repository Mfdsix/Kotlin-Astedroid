package com.mfdsix.astedroid.home

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.mfdsix.astedroid.R
import com.mfdsix.astedroid.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun goToFavorite(){
        val uri = Uri.parse("astedroid://favorite")
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }
}