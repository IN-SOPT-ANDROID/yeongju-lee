package org.sopt.sample.presentation.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivityHomeBinding
import org.sopt.sample.presentation.home.gallery.GalleryFragment
import org.sopt.sample.presentation.home.home.HomeFragment
import org.sopt.sample.presentation.home.search.SearchFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
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
