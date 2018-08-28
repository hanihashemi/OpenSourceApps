package ir.opensourceapps.android

import android.app.Application
import android.support.v7.app.AppCompatDelegate
import ir.opensourceapps.android.di.appModule
import ir.opensourceapps.android.util.CrashReportingTree
import org.koin.android.ext.android.startKoin
import timber.log.Timber

open class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        initTimber()
    }

    protected open fun startKoin() {
        startKoin(this, listOf(appModule))
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
    }
}