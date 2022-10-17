package org.sopt.sample.presentation.home.home

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
        get() = requireNotNull(_binding) ?: throw IllegalArgumentException("fragment is null")
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

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
        homeAdapter.repoList.addAll(
            listOf(
                RepoData(0, "", ""),
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
        homeAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
