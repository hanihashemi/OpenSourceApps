package ir.opensourceapps.android

import ir.opensourceapps.android.di.appModuleTest
import org.koin.android.ext.android.startKoin

class AppTest : App() {
    override fun startKoin() {
        startKoin(this, listOf(appModuleTest))
    }
}