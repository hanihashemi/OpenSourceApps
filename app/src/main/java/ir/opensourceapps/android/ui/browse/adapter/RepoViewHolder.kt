package ir.opensourceapps.android.ui.browse.adapter

import android.support.v7.widget.RecyclerView
import ir.opensourceapps.android.databinding.ListItemRepoBinding
import ir.opensourceapps.android.model.Repo

class RepoViewHolder(
        private val binding: ListItemRepoBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Repo) {
        binding.run {
            repo = item
        }
    }

}

interface RepoListener {
    fun onRepoClick(repo: Repo)
}