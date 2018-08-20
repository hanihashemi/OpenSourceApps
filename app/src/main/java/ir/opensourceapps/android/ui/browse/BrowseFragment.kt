package ir.opensourceapps.android.ui.browse

import ir.opensourceapps.android.R
import ir.opensourceapps.android.base.BaseFragment

class BrowseFragment() : BaseFragment() {

    override val layoutResource: Int = R.layout.browse_fragment

    companion object {
        fun instance(): BrowseFragment {
            return BrowseFragment()
        }
    }

    override fun customizeUI() {

    }
}