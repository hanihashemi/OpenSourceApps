package ir.opensourceapps.android.ui.browse.adapter

import android.support.v7.util.DiffUtil
import ir.opensourceapps.android.model.Repo

class RepoDiffCallback : DiffUtil.ItemCallback<Repo>() {

    override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
        return oldItem != newItem
    }
}