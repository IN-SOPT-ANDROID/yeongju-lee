package org.sopt.sample.presentation.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivityMainBinding
import org.sopt.sample.entity.User
import org.sopt.sample.presentation.home.gallery.GalleryFragment
import org.sopt.sample.presentation.home.home.HomeFragment
import org.sopt.sample.presentation.home.search.SearchFragment
import org.sopt.sample.presentation.login.SignInActivity
import org.sopt.sample.util.base.BaseActivity
import org.sopt.sample.util.extensions.showToast

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val homeViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = homeViewModel
        getUserInfo()
        observeLogout()
    }

    private fun getUserInfo() {
        val userInfo = intent.getSerializableExtra("userInfo") as User
        homeViewModel.id.value = getString(R.string.home_expressed_name, userInfo.id)
        homeViewModel.mbti.value = getString(R.string.home_expressed_mbti, userInfo.mbti)
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
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initTransactionEvent()
    }

    private fun initTransactionEvent() {
        changeFragment(HomeFragment())
        binding.botNavHome.setOnItemSelectedListener setOnItemReselectedListener@{
            when (it.itemId) {
                R.id.menu_home -> {
                    changeFragment(HomeFragment())
                    return@setOnItemReselectedListener true
                }
                R.id.menu_gallery -> {
                    changeFragment(GalleryFragment())
                    return@setOnItemReselectedListener true
                }
                R.id.menu_search -> {
                    changeFragment(SearchFragment())
                    return@setOnItemReselectedListener true
                }
                else -> error("item id :${it.itemId}) is cannot be selected")
            }
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fc_home, fragment)
            .commit()
    }
}
