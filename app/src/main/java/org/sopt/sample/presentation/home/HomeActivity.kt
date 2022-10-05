package org.sopt.sample.presentation.home

import android.os.Bundle
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivityHomeBinding
import org.sopt.sample.entity.User
import org.sopt.sample.util.binding.BaseActivity

class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getUserInfo()
    }

    private fun getUserInfo() {
        val userInfo = intent.getSerializableExtra("userInfo") as User
        binding.tvHomeName.text = getString(R.string.home_name_is, userInfo.id)
        binding.tvHomeMbti.text = getString(R.string.home_mbti_is, userInfo.mbti)
    }
}
