package com.yjooooo.sopt28th.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yjooooo.sopt28th.R
import com.yjooooo.sopt28th.databinding.ItemRcvRepositoryBinding
import com.yjooooo.sopt28th.ui.home.model.ResponseRepository

class RepoRcvAdapter :
    ListAdapter<ResponseRepository, RepoRcvAdapter.RepoRcvViewHolder>(RepoRcvDiffUtil()) {

    class RepoRcvViewHolder(private val binding: ItemRcvRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(repoData: ResponseRepository) {
            if (repoData.language == null) {
                repoData.language = "x"
            }
            binding.repoData = repoData
        }
    }

    override fun onBindViewHolder(holder: RepoRcvViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoRcvViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemRcvRepositoryBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_rcv_repository, parent, false)
        return RepoRcvViewHolder(binding)
    }

    private class RepoRcvDiffUtil : DiffUtil.ItemCallback<ResponseRepository>() {
        override fun areContentsTheSame(
            oldItem: ResponseRepository,
            newItem: ResponseRepository
        ): Boolean =
            oldItem == newItem

        override fun areItemsTheSame(
            oldItem: ResponseRepository,
            newItem: ResponseRepository
        ): Boolean =
            oldItem.name == newItem.name
    }
}