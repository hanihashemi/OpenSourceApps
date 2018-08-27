package ir.opensourceapps.android.data.network.api

import retrofit2.Retrofit

class ApiProvider(private val retrofit: Retrofit) {
    fun repo(): RepoApi = retrofit.create(RepoApi::class.java)
}