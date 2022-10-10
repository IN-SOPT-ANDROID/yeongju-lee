package org.sopt.sample.presentation.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
        supportFragmentManager.beginTransaction().add(R.id.fc_home, HomeFragment()).commit()
        binding.botNavHome.setOnItemSelectedListener {
            val transaction = supportFragmentManager.beginTransaction()
            when (it.itemId) {
                R.id.menu_home -> transaction.replace(R.id.fc_home, HomeFragment())
                R.id.menu_gallery -> transaction.replace(
                    R.id.fc_home,
                    GalleryFragment()
                )
                R.id.menu_search -> transaction.replace(
                    R.id.fc_home,
                    SearchFragment()
                )
                else -> error("item id :${it.itemId}) is cannot be selected")
            }
            transaction.commit()
            true
        }
    }
}
