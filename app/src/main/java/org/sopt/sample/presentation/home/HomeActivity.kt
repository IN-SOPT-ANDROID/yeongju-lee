package org.sopt.sample.presentation.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.R
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
        val userId = intent.getStringExtra("userId")
        val userMbti = intent.getStringExtra("userMbti")
        binding.tvHomeName.text = getString(R.string.home_name_is, userId)
        binding.tvHomeMbti.text = getString(R.string.home_mbti_is, userMbti)
    }
}
