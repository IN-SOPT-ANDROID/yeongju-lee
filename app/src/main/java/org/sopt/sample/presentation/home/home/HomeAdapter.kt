package org.sopt.sample.presentation.home.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.data.entity.Follower
import org.sopt.sample.databinding.ItemHomeBodyBinding
import org.sopt.sample.databinding.ItemHomeHeaderBinding
import org.sopt.sample.util.extensions.ItemDiffCallback

class HomeAdapter() : ListAdapter<Follower, RecyclerView.ViewHolder>(homeDiffUtil) {

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
        val currentPosition = getItem(position)
        when (holder) {
            is HeaderViewHolder -> Unit
            is BodyViewHolder -> if (currentPosition != null) holder.onBind(currentPosition)
        }
    }

    class BodyViewHolder(
        private val binding: ItemHomeBodyBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Follower) {
            binding.data = data
        }
    }

    class HeaderViewHolder(binding: ItemHomeHeaderBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private val homeDiffUtil = ItemDiffCallback<Follower>(
            onItemsTheSame = { old, new -> old.id == new.id },
            onContentsTheSame = { old, new -> old == new }
        )
        private const val HEADER = 0
        private const val BODY = 1
    }
}
