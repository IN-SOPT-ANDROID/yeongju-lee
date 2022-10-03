package org.sopt.sample.presentation.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getUserInfo()
    }

    private fun getUserInfo() {
        binding.tvHomeName.text = "이름 : " + intent.getStringExtra("userId")
        binding.tvHomeMbti.text = "MBTI : " + intent.getStringExtra("userMbti")
    }
}
