package ir.opensourceapps.android.ui.repo

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.opensourceapps.android.base.Fragment
import ir.opensourceapps.android.data.repository.Resource
import ir.opensourceapps.android.data.repository.Status
import ir.opensourceapps.android.databinding.RepoFragmentBinding
import ir.opensourceapps.android.model.Content
import ir.opensourceapps.android.model.Repo
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class RepoFragment() : Fragment() {
    companion object {
        private const val ARG_REPO = "repo"

        fun instance(repo: Repo): RepoFragment {
            val arguments = Bundle()
            arguments.putParcelable(ARG_REPO, repo)

            val fragment = RepoFragment()
            fragment.arguments = arguments

            return fragment
        }
    }

    private var repo: Repo? = null
    private val vm: RepoViewModel by viewModel()


    override fun customizeUI() {
        vm.readmeObserver.observe(this, Observer<Resource<Content>> {
            when (it?.status) {
                Status.SUCCESS -> {
                    Timber.d("======> YES")
                }
                Status.LOADING -> Timber.d("======> Loading")
                else -> {
                    Timber.d("======> NO")
                }
            }
        })
        if (repo != null)
            vm.getReadme(repo!!)
    }

    override fun inflateLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        val binding = RepoFragmentBinding.inflate(inflater, container, false)
        binding.repo = repo
        return binding.root
    }

    override fun gatherArguments(bundle: Bundle) {
        if (bundle.containsKey(ARG_REPO))
            repo = bundle.getParcelable(ARG_REPO)
    }
}