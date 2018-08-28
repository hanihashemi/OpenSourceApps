package ir.opensourceapps.android.di

import ir.opensourceapps.android.data.network.RetrofitUtil
import ir.opensourceapps.android.data.network.api.ApiProvider
import ir.opensourceapps.android.data.repository.RepoPagingRepository
import ir.opensourceapps.android.data.repository.RepoRepository
import ir.opensourceapps.android.ui.browse.BrowseViewModel
import ir.opensourceapps.android.ui.repo.RepoViewModel
import ir.opensourceapps.android.util.AppExecutors
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val appModuleTest: Module = module {

    single { AppExecutors() }

    //ViewModel
    viewModel<BrowseViewModel>()
    viewModel<RepoViewModel>()

    //Network
    single { RetrofitUtil.create("http://localhost:3300/") }

    //API
    single { ApiProvider(get()).repo() }

    //Repository
    single<RepoPagingRepository>()
    single<RepoRepository>()
}