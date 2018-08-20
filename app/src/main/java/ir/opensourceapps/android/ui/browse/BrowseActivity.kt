package ir.opensourceapps.android.ui.browse

import android.support.v4.app.Fragment
import ir.opensourceapps.android.base.BaseActivityWithSingleFragment

class BrowseActivity : BaseActivityWithSingleFragment() {
    override fun createFragment(): Fragment = BrowseFragment.instance()
}
