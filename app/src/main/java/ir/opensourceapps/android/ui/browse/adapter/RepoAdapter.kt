package ir.opensourceapps.android.ui.browse.adapter

import android.arch.paging.PagedListAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import ir.opensourceapps.android.databinding.ListItemRepoBinding
import ir.opensourceapps.android.model.Repo

class RepoAdapter(private val listener: RepoListener) :
        PagedListAdapter<Repo, RepoViewHolder>(RepoDiffCallback()) {

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = getItem(position) ?: return
        holder.bind(repo, listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RepoViewHolder {
        return RepoViewHolder(ListItemRepoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }
}