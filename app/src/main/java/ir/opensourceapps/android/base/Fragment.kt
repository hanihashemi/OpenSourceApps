package ir.opensourceapps.android.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class Fragment : Fragment() {
    private lateinit var view: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (arguments != null)
            gatherArguments(arguments!!)

        view = inflateLayout(inflater, container)
        customizeUI()
        return view
    }

    override fun getView() = view

    abstract fun inflateLayout(inflater: LayoutInflater, container: ViewGroup?): View

    protected abstract fun customizeUI()

    protected open fun gatherArguments(bundle: Bundle) {}
}