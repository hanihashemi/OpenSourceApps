package ir.opensourceapps.android.di

import ir.opensourceapps.android.data.network.RetrofitUtil
import ir.opensourceapps.android.data.network.api.RepoApi
import ir.opensourceapps.android.util.AppExecutors
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val appModule: Module = module {

    single { AppExecutors() }

    //Network
    single { RetrofitUtil.create() }

    //API
    single<RepoApi>()
}