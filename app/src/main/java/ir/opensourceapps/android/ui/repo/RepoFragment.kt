package ir.opensourceapps.android.ui.repo

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import ir.opensourceapps.android.R
import ir.opensourceapps.android.base.Fragment
import ir.opensourceapps.android.data.repository.Resource
import ir.opensourceapps.android.data.repository.Status
import ir.opensourceapps.android.databinding.RepoFragmentBinding
import ir.opensourceapps.android.model.Content
import ir.opensourceapps.android.model.Repo
import kotlinx.android.synthetic.main.repo_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel
import ru.noties.markwon.Markwon

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
        observeResponse()
        fetchRepo()
    }

    private fun observeResponse() {
        vm.readmeObserver.observe(this, Observer<Resource<Content>> {
            when (it?.status) {
                Status.SUCCESS -> {
                    val bytesEncoded = Base64.decode(it.data?.content, Base64.DEFAULT)
                    Markwon.setMarkdown(markdownView, String(bytesEncoded, Charsets.UTF_8))
                    progressBar.visibility = GONE
                }
                Status.LOADING -> progressBar.visibility = VISIBLE
                else -> {
                    progressBar.visibility = GONE
                    showErrorMessage(it)
                }
            }
        })
    }

    private fun showErrorMessage(it: Resource<Content>?) {
        val snackBar = Snackbar.make(
                scrollView,
                it?.message.toString(),
                Snackbar.LENGTH_INDEFINITE)
        snackBar.setAction(R.string.action_retry) { _ ->
            fetchRepo()
        }.show()
    }

    private fun fetchRepo() {
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