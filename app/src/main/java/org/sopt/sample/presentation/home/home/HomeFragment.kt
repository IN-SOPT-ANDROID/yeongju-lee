package org.sopt.sample.presentation.home.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.sample.R
import org.sopt.sample.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding!!
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        getUserInfo()
        initAdapter()
        return binding.root
    }

    private fun initAdapter() {
        homeAdapter = HomeAdapter()
        binding.rvHome.adapter = homeAdapter
        homeAdapter.repoList.addAll(
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
                RepoData(R.drawable.img_profile, "Plan", "Nunu Lee")
            )
        )
        homeAdapter.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getUserInfo()
    }

    private fun getUserInfo() {
        val intent = Intent()
        val userId = intent.getStringExtra("userId")
        val userMbti = intent.getStringExtra("userMbti")
        binding.tvHomeName.text = getString(R.string.home_expressed_name, userId)
        binding.tvHomeMbti.text = getString(R.string.home_expressed_mbti, userMbti)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
