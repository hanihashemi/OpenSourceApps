package ir.opensourceapps.android

import android.app.Application
import android.content.Context
import android.support.test.runner.AndroidJUnitRunner

class CustomAndroidJUnitRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, AppTest::class.java.name, context)
    }
}