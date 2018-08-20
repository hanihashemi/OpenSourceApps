package ir.opensourceapps.android.ui.browse.adapter

import android.support.v7.recyclerview.extensions.ListAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.opensourceapps.android.databinding.ListItemRepoBinding
import ir.opensourceapps.android.model.Repo

class RepoAdapter(
        private val onRepoClick: View.OnClickListener) :
        ListAdapter<Repo, RepoViewHolder>(RepoDiffCallback()) {

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = getItem(position)
        holder.bind(onRepoClick, repo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RepoViewHolder {
        return RepoViewHolder(ListItemRepoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }
}