package org.sopt.sample.presentation.home.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import org.sopt.sample.R
import org.sopt.sample.databinding.FragmentHomeBinding
import org.sopt.sample.entity.RepoData
import org.sopt.sample.entity.User
import org.sopt.sample.presentation.login.SignInActivity
import org.sopt.sample.util.base.BaseFragment
import org.sopt.sample.util.extensions.showToast

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val homeViewModel: HomeViewModel by activityViewModels()
    private lateinit var homeAdapter: HomeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getUserInfo()
        initAdapter()
    }

    private fun getUserInfo() {
        val userInfo = requireActivity().intent.getSerializableExtra("userInfo") as User
        homeViewModel.id.value = getString(R.string.home_expressed_name, userInfo.id)
        homeViewModel.mbti.value = getString(R.string.home_expressed_mbti, userInfo.mbti)
    }

    private fun onClickLogout() {
        homeViewModel.logout.observe(viewLifecycleOwner) { logout ->
            if (logout) {
                requireActivity().showToast(getString(R.string.success_logout))
                val toLogin = Intent(requireActivity(), SignInActivity::class.java)
                startActivity(toLogin)
                requireActivity().finish()
                Log.d("asdf", "$logout")
            }
        }
    }

    private fun initAdapter() {
        homeAdapter = HomeAdapter()
        binding.rvHome.adapter = homeAdapter
        initRepoData()
    }

    private fun initRepoData() {
        homeAdapter.submitList(
            listOf(
                RepoData(0, "", ""),
                RepoData(R.drawable.img_jandi, "Android", "Yeongju Lee"),
                RepoData(R.drawable.img_jandi, "Server", "Daehwan Gye"),
                RepoData(R.drawable.img_jandi, "IOS", "Hajeong Kim"),
                RepoData(R.drawable.img_jandi, "Design", "Jieun Kim"),
                RepoData(R.drawable.img_jandi, "Plan", "Nunu Lee"),
                RepoData(R.drawable.img_jandi, "Android", "Yeongju Lee"),
                RepoData(R.drawable.img_jandi, "Server", "Daehwan Gye"),
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
