package ir.opensourceapps.android.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.support.annotation.MainThread
import ir.opensourceapps.android.data.network.adapter.ApiEmptyResponse
import ir.opensourceapps.android.data.network.adapter.ApiErrorResponse
import ir.opensourceapps.android.data.network.adapter.ApiResponse
import ir.opensourceapps.android.data.network.adapter.ApiSuccessResponse
import ir.opensourceapps.android.util.AppExecutors

abstract class NetworkBoundResource<ResultType>
@MainThread constructor(private val appExecutors: AppExecutors) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading()
        fetchFromNetwork()
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    private fun fetchFromNetwork() {
        val apiResponse = createCall()

        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            when (response) {
                is ApiSuccessResponse -> {
                    appExecutors.mainThread().execute { setValue(Resource.success(response.body)) }
                }
                is ApiEmptyResponse -> {
                    appExecutors.mainThread().execute {
                        setValue(Resource.success(null))
                    }
                }
                is ApiErrorResponse -> {
                    setValue(Resource.error(response.errorMessage))
                }
            }
        }
    }

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<ResultType>>
}