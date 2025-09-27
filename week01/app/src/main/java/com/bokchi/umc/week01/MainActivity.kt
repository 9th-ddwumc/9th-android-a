package com.bokchi.umc.week01

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private lateinit var tvHappy: TextView
    private lateinit var imgHappy: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // 행복 우표 선택
        tvHappy = findViewById(R.id.tvHappy)
        imgHappy = findViewById(R.id.imgHappy)

        val HappyColor = ContextCompat.getColor(this, R.color.light_yellow)

        imgHappy.setOnClickListener {
            tvHappy.setTextColor(HappyColor)
            Toast.makeText(this, "행복 스탬프를 선택했어요", Toast.LENGTH_SHORT).show()
        }

        // 흥분 우표 선택
        val tvExcited = findViewById<TextView>(R.id.tvExcited)
        val imgExcited = findViewById<ImageView>(R.id.imgExcited)

        val ExcitedColor = ContextCompat.getColor(this, R.color.light_sky_blue)

        imgExcited.setOnClickListener {
            tvExcited.setTextColor(ExcitedColor)
            Toast.makeText(this, "흥분 스탬프를 선택했어요", Toast.LENGTH_SHORT).show()
        }

        // 보통 우표 선택
        val tvNormal = findViewById<TextView>(R.id.tvNormal)
        val imgNormal = findViewById<ImageView>(R.id.imgNormal)

        val NormalColor = ContextCompat.getColor(this, R.color.light_lavender)

        imgNormal.setOnClickListener {
            tvNormal.setTextColor(NormalColor)
            Toast.makeText(this, "평범 스탬프를 선택했어요", Toast.LENGTH_SHORT).show()
        }

        // 불안 우표 선택
        val tvNervous = findViewById<TextView>(R.id.tvNervous)
        val imgNervous = findViewById<ImageView>(R.id.imgNervous)

        val NervousColor = ContextCompat.getColor(this, R.color.light_green)

        imgNervous.setOnClickListener {
            tvNervous.setTextColor(NervousColor)
            Toast.makeText(this, "불안 스탬프를 선택했어요", Toast.LENGTH_SHORT).show()
        }

        // 화남 우표 선택
        val tvAngry = findViewById<TextView>(R.id.tvAngry)
        val imgAngry = findViewById<ImageView>(R.id.imgAngry)

        val AngryColor = ContextCompat.getColor(this, R.color.light_red)

        imgAngry.setOnClickListener {
            tvAngry.setTextColor(AngryColor)
            Toast.makeText(this, "화남 스탬프를 선택했어요", Toast.LENGTH_SHORT).show()
        }


    }
}