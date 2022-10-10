package org.sopt.sample.presentation.home.home

import android.os.Bundle
import android.view.View
import org.sopt.sample.R
import org.sopt.sample.databinding.FragmentHomeBinding
import org.sopt.sample.entity.RepoData
import org.sopt.sample.util.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var homeAdapter: HomeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        homeAdapter = HomeAdapter()
        binding.rvHome.adapter = homeAdapter
        initRepoData()
    }

    private fun initRepoData() {
        homeAdapter.submitList(
            listOf(
                RepoData(R.drawable.img_profile, "Android", "Yeongju Lee"),
                RepoData(R.drawable.img_profile, "Server", "Daehwan Gye"),
                RepoData(R.drawable.img_profile, "IOS", "Hajeong Kim"),
                RepoData(R.drawable.img_profile, "Design", "Jieun Kim"),
                RepoData(R.drawable.img_profile, "Plan", "Nunu Lee"),
                RepoData(R.drawable.img_profile, "Android", "Yeongju Lee"),
                RepoData(R.drawable.img_profile, "Server", "Daehwan Gye"),
                RepoData(R.drawable.img_profile, "IOS", "Hajeong Kim"),
                RepoData(R.drawable.img_profile, "Design", "Jieun Kim"),
                RepoData(R.drawable.img_profile, "Plan", "Nunu Lee"),
                RepoData(R.drawable.img_profile, "Android", "Yeongju Lee"),
                RepoData(R.drawable.img_profile, "Server", "Daehwan Gye"),
                RepoData(R.drawable.img_profile, "IOS", "Hajeong Kim"),
                RepoData(R.drawable.img_profile, "Design", "Jieun Kim"),
                RepoData(R.drawable.img_profile, "Plan", "Nunu Lee"),
                RepoData(R.drawable.img_profile, "Android", "Yeongju Lee"),
                RepoData(R.drawable.img_profile, "Server", "Daehwan Gye"),
                RepoData(R.drawable.img_profile, "IOS", "Hajeong Kim"),
                RepoData(R.drawable.img_profile, "Design", "Jieun Kim"),
                RepoData(R.drawable.img_profile, "Plan", "Nunu Lee")
            )
        )
    }
}
