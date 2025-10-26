package com.example.umc_9th.flo_clone

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import com.example.umc_9th.flo_clone.databinding.ActivitySplashBinding
import java.util.logging.Handler

class SplashActiity: AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val handler = android.os.Handler(Looper.getMainLooper())

        handler.postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
        }, 1000)
    }
}