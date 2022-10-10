package org.sopt.sample.presentation.home.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.databinding.ItemHomeBodyBinding
import org.sopt.sample.databinding.ItemHomeHeaderBinding
import org.sopt.sample.entity.RepoData

class HomeAdapter : ListAdapter<RepoData, RecyclerView.ViewHolder>(homeDiffUtil) {
    private lateinit var itemHomeHeaderBinding: ItemHomeHeaderBinding
    private lateinit var itemHomeBodyBinding: ItemHomeBodyBinding

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> HEADER
            else -> BODY
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADER -> {
                itemHomeHeaderBinding =
                    ItemHomeHeaderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                HeaderViewHolder(itemHomeHeaderBinding)
            }
            BODY -> {
                itemHomeBodyBinding =
                    ItemHomeBodyBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                BodyViewHolder(itemHomeBodyBinding)
            }
            else -> {
                throw RuntimeException("알 수 없는 viewType error")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BodyViewHolder) {
            holder.onBind(getItem(position))
        } else if (holder is HeaderViewHolder) {
            holder
        }
    }

    class BodyViewHolder(
        private val binding: ItemHomeBodyBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RepoData) {
            binding.ivItemHomeProfile.setImageResource(data.img)
            binding.tvItemHomeRepo.text = data.repo
            binding.tvItemHomeName.text = data.name
        }
    }

    class HeaderViewHolder(
        private val binding: ItemHomeHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private val homeDiffUtil = object : DiffUtil.ItemCallback<RepoData>() {
            override fun areItemsTheSame(oldItem: RepoData, newItem: RepoData): Boolean =
                oldItem.repo == newItem.repo

            override fun areContentsTheSame(oldItem: RepoData, newItem: RepoData): Boolean =
                oldItem == newItem
        }

        private const val HEADER = 0
        private const val BODY = 1
    }
}
