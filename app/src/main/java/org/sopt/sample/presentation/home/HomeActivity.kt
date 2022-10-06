package org.sopt.sample.presentation.home

import android.os.Bundle
import androidx.activity.viewModels
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivityHomeBinding
import org.sopt.sample.entity.User
import org.sopt.sample.util.base.BaseActivity

class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {
    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = homeViewModel
        getUserInfo()
    }

    private fun getUserInfo() {
        val userInfo = intent.getSerializableExtra("userInfo") as User
        homeViewModel.id.value = getString(R.string.home_id_is, userInfo.id)
        homeViewModel.mbti.value = getString(R.string.home_mbti_is, userInfo.mbti)
    }
}
