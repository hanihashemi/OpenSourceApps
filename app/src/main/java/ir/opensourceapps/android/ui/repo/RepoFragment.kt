package ir.opensourceapps.android.ui.repo

import android.os.Bundle
import ir.opensourceapps.android.R
import ir.opensourceapps.android.base.BaseFragment
import ir.opensourceapps.android.model.Repo
import timber.log.Timber

class RepoFragment() : BaseFragment() {
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

    override val layoutResource: Int = R.layout.repo_fragment

    override fun customizeUI() {
        Timber.d("====> ${repo?.description}")
    }

    override fun gatherArguments(bundle: Bundle?) {
        if (bundle != null && bundle.containsKey(ARG_REPO))
            repo = bundle.getParcelable(ARG_REPO)
    }
}