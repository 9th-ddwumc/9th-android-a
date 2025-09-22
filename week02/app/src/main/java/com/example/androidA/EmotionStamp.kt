package com.example.androidA

import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import com.example.androidA.databinding.StampEmotionBinding
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class EmotionStamp : AppCompatActivity() {
    private lateinit var binding: StampEmotionBinding // View 참조 가능
    /*
    Activity가 생성되기 전에 클래스 멤버에서 바로 val binding = LayoutBinding.inflate(...)을
    쓰면 layoutInflater가 아직 준비 안 됨 → 런타임 에러 발생. Activity의 생명주기 문제.
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = StampEmotionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* 전통적 방식
         val imgHappy = findViewById<ImageView>(R.id.happy_image)
         imgHappy.setOnClickListener { ... } */

        // View Binding 방식
        val happyColor = ContextCompat.getColor(this, R.color.yellow)
        val excitingColor = ContextCompat.getColor(this, R.color.skyblue)
        val commonColor = ContextCompat.getColor(this, R.color.purple)
        val anxiousColor = ContextCompat.getColor(this, R.color.green)
        val angryColor = ContextCompat.getColor(this, R.color.red)
        val originalColor = binding.happyText.currentTextColor

        binding.happyImage.setOnClickListener {
            binding.happyText.setTextColor(happyColor)
            Toast.makeText(this, "오늘 하루 중 가장 기뻤던 순간은 언제였나요?", Toast.LENGTH_SHORT).show()

            binding.happyImage.postDelayed({
                binding.happyText.setTextColor(originalColor) // 원래 색으로
            }, 3000)
        }

        binding.excitingImage.setOnClickListener {
            binding.excitingText.setTextColor(excitingColor)
            Toast.makeText(this, "오늘 기대되는 일이 있다면 무엇인가요?", Toast.LENGTH_SHORT).show()

            binding.excitingImage.postDelayed({
                binding.excitingText.setTextColor(originalColor)
            }, 3000)
        }

        binding.commonImage.setOnClickListener {
            binding.commonText.setTextColor(commonColor)
            Toast.makeText(this, "평범한 하루 중 소중하게 느낀 순간은 무엇인가요?", Toast.LENGTH_SHORT).show()

            binding.commonImage.postDelayed({
                binding.commonText.setTextColor(originalColor)
            }, 3000)
        }

        binding.anxiousImage.setOnClickListener {
            binding.anxiousText.setTextColor(anxiousColor)
            Toast.makeText(this, "최근 가장 걱정되는 일이 무엇인가요?", Toast.LENGTH_SHORT).show()

            binding.anxiousImage.postDelayed({
                binding.anxiousText.setTextColor(originalColor)
            }, 3000)
        }

        binding.angryImage.setOnClickListener {
            binding.angryText.setTextColor(angryColor)
            Toast.makeText(this, "최근에 가장 화가 났던 상황은 무엇인가요?", Toast.LENGTH_SHORT).show()

            binding.angryImage.postDelayed({
                binding.angryText.setTextColor(originalColor)
            }, 3000)
        }
    }
}
