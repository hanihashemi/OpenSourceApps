package ir.opensourceapps.android.ui.browse.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import ir.opensourceapps.android.databinding.ListItemRepoBinding
import ir.opensourceapps.android.model.Repo

class RepoViewHolder(
        private val binding: ListItemRepoBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(listener: View.OnClickListener, item: Repo) {
        binding.run {
            clickListener = listener
            repo = item
        }
    }
}