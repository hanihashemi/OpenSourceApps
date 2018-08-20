package ir.opensourceapps.android.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment : Fragment() {
    private var view: View? = null

    protected abstract val layoutResource: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = inflater.inflate(layoutResource, container, false)
        if (arguments != null)
            gatherArguments(arguments)
        customizeUI()
        return view
    }

    override fun getView(): View? {
        return view
    }

    protected abstract fun customizeUI()

    protected fun gatherArguments(bundle: Bundle?) {}
}