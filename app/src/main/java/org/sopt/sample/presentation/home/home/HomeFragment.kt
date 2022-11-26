package org.sopt.sample.presentation.home.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.sample.R
import org.sopt.sample.databinding.FragmentHomeBinding
import org.sopt.sample.presentation.login.SignInActivity
import org.sopt.sample.util.binding.BindingFragment
import org.sopt.sample.util.extensions.showToast

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val homeViewModel: HomeViewModel by activityViewModels()
    private lateinit var homeAdapter: HomeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun onClickLogout() {
        homeViewModel.logout()
        requireActivity().showToast(getString(R.string.success_logout))
        val toLogin = Intent(requireActivity(), SignInActivity::class.java)
        startActivity(toLogin)
        requireActivity().finish()
    }

    private fun initAdapter() {
        homeViewModel.followerInfo.observe(viewLifecycleOwner) { follower ->
            homeAdapter = HomeAdapter(
                onClickLogout = ::onClickLogout,
                requireNotNull(homeViewModel.userInfo.value)
            )
            binding.rvHome.adapter = homeAdapter
            homeAdapter.submitList(follower)
        }
    }
}
