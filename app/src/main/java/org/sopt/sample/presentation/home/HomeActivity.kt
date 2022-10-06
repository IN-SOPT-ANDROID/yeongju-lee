package org.sopt.sample.presentation.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivityHomeBinding
import org.sopt.sample.entity.User
import org.sopt.sample.presentation.login.SignInActivity
import org.sopt.sample.util.base.BaseActivity
import org.sopt.sample.util.extensions.showToast

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {
    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = homeViewModel
        getUserInfo()
        observeLogout()
    }

    private fun getUserInfo() {
        val userInfo = intent.getSerializableExtra("userInfo") as User
        homeViewModel.id.value = getString(R.string.home_id_is, userInfo.id)
        homeViewModel.mbti.value = getString(R.string.home_mbti_is, userInfo.mbti)
    }

    private fun observeLogout() {
        homeViewModel.logout.observe(this) { logout ->
            if (logout) {
                showToast(getString(R.string.success_logout))
                val toLogin = Intent(this, SignInActivity::class.java)
                startActivity(toLogin)
                finish()
            }
        }
    }
}
