package ir.opensourceapps.android.base

import android.content.Intent
import android.support.v4.app.Fragment
import ir.opensourceapps.android.R

abstract class BaseActivityWithSingleFragment : BaseActivity() {
    private var fragmentTag: String? = null

    override val layoutResource: Int
        get() = R.layout.activity_with_single_fragment

    protected val fragment: Fragment?
        get() = supportFragmentManager.findFragmentByTag(fragmentTag)

    protected abstract fun createFragment(): Fragment

    override fun customizeUI() {
        val fragment = createFragment()
        fragmentTag = fragment::class.java.getSimpleName()

        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment, fragmentTag)
                .commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val fragment = supportFragmentManager.findFragmentByTag(fragmentTag)
        fragment?.onActivityResult(requestCode, resultCode, data)
    }
}