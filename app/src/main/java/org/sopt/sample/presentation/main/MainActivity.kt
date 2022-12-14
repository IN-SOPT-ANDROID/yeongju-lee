package org.sopt.sample.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivityMainBinding
import org.sopt.sample.presentation.home.gallery.GalleryFragment
import org.sopt.sample.presentation.home.home.HomeFragment
import org.sopt.sample.presentation.home.search.SearchFragment
import org.sopt.sample.util.binding.BindingActivity

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTransactionEvent()
    }

    private fun initTransactionEvent() {
        changeFragment(HomeFragment())
        binding.botNavHome.setOnItemSelectedListener setOnItemReselectedListener@{ menu ->
            when (menu.itemId) {
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
                else -> error("item id :${menu.itemId}) is cannot be selected")
            }
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fc_home, fragment)
            .commit()
    }
}
