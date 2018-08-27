package ir.opensourceapps.android.di

import ir.opensourceapps.android.data.network.RetrofitUtil
import ir.opensourceapps.android.data.network.api.SearchApi
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val appModule: Module = module {

    //Network
    single { RetrofitUtil.create() }

    //API
    single<SearchApi>()
}