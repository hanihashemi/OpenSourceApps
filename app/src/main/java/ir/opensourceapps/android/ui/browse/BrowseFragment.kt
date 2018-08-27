package ir.opensourceapps.android.ui.browse

import android.arch.lifecycle.Observer
import android.support.v7.widget.LinearLayoutManager
import ir.opensourceapps.android.R
import ir.opensourceapps.android.base.BaseFragment
import ir.opensourceapps.android.model.Repo
import ir.opensourceapps.android.ui.browse.adapter.RepoAdapter
import ir.opensourceapps.android.ui.browse.adapter.RepoListener
import ir.opensourceapps.android.ui.repo.RepoActivity
import kotlinx.android.synthetic.main.browse_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class BrowseFragment() : BaseFragment(), RepoListener {
    companion object {
        fun instance(): BrowseFragment {
            return BrowseFragment()
        }
    }

    override val layoutResource: Int = R.layout.browse_fragment
    private val vm: BrowseViewModel by viewModel()

    override fun customizeUI() {
        rclRepo.layoutManager = LinearLayoutManager(context!!)
        rclRepo.adapter = RepoAdapter(this)

        vm.list.observe(this, Observer {
            (rclRepo.adapter as RepoAdapter).submitList(it)
        })

        vm.searchIt("")
    }

    override fun onRepoClick(repo: Repo) = RepoActivity.start(context!!, repo)
}