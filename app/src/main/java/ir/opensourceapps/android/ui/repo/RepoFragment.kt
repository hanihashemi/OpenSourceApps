package ir.opensourceapps.android.ui.repo

import ir.opensourceapps.android.R
import ir.opensourceapps.android.base.BaseFragment

class RepoFragment() : BaseFragment() {
    companion object {
        fun instance(): RepoFragment {
            return RepoFragment()
        }
    }

    override val layoutResource: Int = R.layout.repo_fragment

    override fun customizeUI() {

    }
}