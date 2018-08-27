package ir.opensourceapps.android.data.repository

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import ir.opensourceapps.android.data.network.datasource.NetworkState

data class Listing<T>(
        val pagedList: LiveData<PagedList<T>>,
        val networkState: LiveData<NetworkState>,
        val refreshState: LiveData<NetworkState>,
        val refresh: () -> Unit,
        val retry: () -> Unit)