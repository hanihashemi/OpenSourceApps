package ir.opensourceapps.android.ui.repo

import android.content.Context
import android.content.Intent
import ir.opensourceapps.android.base.BaseActivityWithSingleFragment

class RepoActivity : BaseActivityWithSingleFragment() {
    companion object {
        fun start(context: Context) {
            val intent = Intent(context, RepoActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun createFragment() = RepoFragment.instance()
}