package ir.opensourceapps.android.data.repository

import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.support.annotation.MainThread
import ir.opensourceapps.android.data.network.api.RepoApi
import ir.opensourceapps.android.data.network.datasource.repo.RepoDataSourceFactory
import ir.opensourceapps.android.model.Repo
import ir.opensourceapps.android.util.AppExecutors

class RepoPagingRepository(private val repoApi: RepoApi,
                           private val appExecutors: AppExecutors) {
    @MainThread
    fun listOfNutritionist(pageSize: Int): Listing<Repo> {
        val sourceFactory = RepoDataSourceFactory(repoApi, appExecutors)

        val pagingConfig = PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setPrefetchDistance(3)
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .build()

        val livePagedList = LivePagedListBuilder(sourceFactory, pagingConfig)
                .setFetchExecutor(appExecutors.networkIO())
                .build()

        val refreshState = Transformations.switchMap(sourceFactory.sourceLiveData) {
            it.initialLoad
        }
        return Listing(
                pagedList = livePagedList,
                networkState = Transformations.switchMap(sourceFactory.sourceLiveData) {
                    it.networkState
                },
                retry = {
                    sourceFactory.sourceLiveData.value?.retryAllFailed()
                },
                refresh = {
                    sourceFactory.sourceLiveData.value?.invalidate()
                },
                refreshState = refreshState
        )
    }
}