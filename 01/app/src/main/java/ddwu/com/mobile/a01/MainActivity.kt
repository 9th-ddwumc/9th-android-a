package ddwu.com.mobile.a01

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ddwu.com.mobile.a01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.happy.setOnClickListener {
            resetColors()
            binding.happyText.setTextColor(Color.parseColor("#FFEFB6"))
            Toast.makeText(this, binding.happyText.text, Toast.LENGTH_SHORT).show()
        }

        binding.excited.setOnClickListener {
            resetColors()
            binding.excitedText.setTextColor(Color.parseColor("#CEE7F5"))
            Toast.makeText(this, binding.excitedText.text, Toast.LENGTH_SHORT).show()
        }

        binding.soso.setOnClickListener {
            resetColors()
            binding.sosoText.setTextColor(Color.parseColor("#BEC3ED"))
            Toast.makeText(this, binding.sosoText.text, Toast.LENGTH_SHORT).show()
        }

        binding.nervous.setOnClickListener {
            resetColors()
            binding.nervousText.setTextColor(Color.parseColor("#B1D3B9"))
            Toast.makeText(this, binding.nervousText.text, Toast.LENGTH_SHORT).show()
        }

        binding.angry.setOnClickListener {
            resetColors()
            binding.angryText.setTextColor(Color.parseColor("#EB8B8B"))
            Toast.makeText(this, binding.angryText.text, Toast.LENGTH_SHORT).show()
        }

        binding.btnSub.setOnClickListener {
            val intent = Intent(this, SubActivity::class.java)
            startActivity(intent)
        }
    }

    fun resetColors() {
        binding.happyText.setTextColor(Color.BLACK)
        binding.excitedText.setTextColor(Color.BLACK)
        binding.sosoText.setTextColor(Color.BLACK)
        binding.nervousText.setTextColor(Color.BLACK)
        binding.angryText.setTextColor(Color.BLACK)
    }

}