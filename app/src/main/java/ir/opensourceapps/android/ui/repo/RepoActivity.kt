package ir.opensourceapps.android.ui.repo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import ir.opensourceapps.android.base.BaseActivityWithSingleFragment
import ir.opensourceapps.android.model.Repo

class RepoActivity : BaseActivityWithSingleFragment() {
    companion object {
        private const val ARG_REPO = "repo"

        fun start(context: Context, repo: Repo) {
            val intent = Intent(context, RepoActivity::class.java)
            intent.putExtra(ARG_REPO, repo)
            context.startActivity(intent)
        }
    }

    private lateinit var repo: Repo

    override fun createFragment() = RepoFragment.instance(repo)

    override fun gatherArguments(bundle: Bundle) {
        super.gatherArguments(bundle)
        repo = bundle.getParcelable<Repo>(ARG_REPO)
    }
}