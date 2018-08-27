package ir.opensourceapps.android.util

import android.util.Log
import timber.log.Timber

class CrashReportingTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO)
            return

//            if (t != null)
//                Crashlytics.logException(t)
    }
}