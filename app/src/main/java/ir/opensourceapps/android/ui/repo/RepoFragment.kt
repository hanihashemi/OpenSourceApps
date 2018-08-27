package ir.opensourceapps.android.ui.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.opensourceapps.android.base.Fragment
import ir.opensourceapps.android.databinding.RepoFragmentBinding
import ir.opensourceapps.android.model.Repo

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


    override fun customizeUI() {

    }

    override fun inflateLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        val binding  = RepoFragmentBinding.inflate(inflater, container, false)
        binding.repo = repo
        return binding.root
    }

    override fun gatherArguments(bundle: Bundle) {
        if (bundle.containsKey(ARG_REPO))
            repo = bundle.getParcelable(ARG_REPO)
    }
}