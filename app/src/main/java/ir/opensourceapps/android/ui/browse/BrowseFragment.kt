package ir.opensourceapps.android.ui.browse

import android.arch.lifecycle.Observer
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.opensourceapps.android.R
import ir.opensourceapps.android.base.Fragment
import ir.opensourceapps.android.data.network.datasource.NetworkState
import ir.opensourceapps.android.data.network.datasource.Status
import ir.opensourceapps.android.model.Repo
import ir.opensourceapps.android.ui.browse.adapter.RepoAdapter
import ir.opensourceapps.android.ui.browse.adapter.RepoListener
import ir.opensourceapps.android.ui.repo.RepoActivity
import kotlinx.android.synthetic.main.browse_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class BrowseFragment : Fragment(), RepoListener {
    companion object {
        fun instance(): BrowseFragment {
            return BrowseFragment()
        }
    }

    private val vm: BrowseViewModel by viewModel()

    override fun customizeUI() {
        initSwipeRefresh()
        initRecycler()
        handleNetworkFail()
        observeList()

        vm.searchIt("")
    }

    private fun observeList() {
        vm.list.observe(this, Observer {
            (rclRepo.adapter as RepoAdapter).submitList(it)
        })
    }

    private fun handleNetworkFail() {
        vm.networkState.observe(this, Observer {
            if (it?.status == Status.FAILED) {
                val snackBar = Snackbar.make(
                        coordinator,
                        it.msg.toString(),
                        Snackbar.LENGTH_INDEFINITE)
                snackBar.setAction(R.string.action_retry) { _ ->
                    vm.retry()
                }.show()
            }
        })
    }

    private fun initSwipeRefresh() {
        swipeRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light)

        swipeRefresh.setOnRefreshListener { vm.refresh() }

        swipeRefresh.isRefreshing = true

        vm.refreshState.observe(this, Observer<NetworkState> {
            swipeRefresh.isRefreshing =
                    it == NetworkState.LOADING
        })
    }

    private fun initRecycler() {
        rclRepo.layoutManager = LinearLayoutManager(context!!)
        rclRepo.adapter = RepoAdapter(this)
    }

    override fun inflateLayout(inflater: LayoutInflater, container: ViewGroup?): View =
            inflater.inflate(R.layout.browse_fragment, container, false)

    override fun onRepoClick(repo: Repo) = RepoActivity.start(context!!, repo)
}