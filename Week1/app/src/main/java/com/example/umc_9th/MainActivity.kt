package com.example.umc_9th

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.umc_9th.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.ivHappy.setOnClickListener {
            binding.tvHappy.setTextColor(resources.getColor(R.color.happy_color, theme))
            Toast.makeText(this, "부럽습니다!", Toast.LENGTH_SHORT).show()
        }

        binding.ivExcited.setOnClickListener {
            binding.tvExcited.setTextColor(resources.getColor(R.color.excited_color, theme))
            Toast.makeText(this, "왜요?", Toast.LENGTH_SHORT).show()
        }

        binding.ivNormal.setOnClickListener {
            binding.tvNormal.setTextColor(resources.getColor(R.color.normal_color, theme))
            Toast.makeText(this, "평범한 날 좋죠~", Toast.LENGTH_SHORT).show()
        }

        binding.ivAnxious.setOnClickListener {
            binding.tvAnxious.setTextColor(resources.getColor(R.color.anxious_color, theme))
            Toast.makeText(this, "즉시 주무세요!", Toast.LENGTH_SHORT).show()
        }

        binding.ivMad.setOnClickListener {
            binding.tvMad.setTextColor(resources.getColor(R.color.mad_color, theme))
            Toast.makeText(this, "무슨 일이 있었나요?", Toast.LENGTH_SHORT).show()
        }

    }
}