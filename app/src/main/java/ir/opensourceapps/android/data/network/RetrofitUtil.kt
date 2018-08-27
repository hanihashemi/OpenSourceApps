package ir.opensourceapps.android.data.network

import ir.opensourceapps.android.BuildConfig
import ir.opensourceapps.android.data.network.adapter.LiveDataCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitUtil {
    companion object {
        private const val base_url = "https://api.github.com/"

        fun create(): Retrofit {
            val client = OkHttpClient().newBuilder()

            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                client.addInterceptor(interceptor)
            }

            return Retrofit.Builder()
                    .baseUrl(base_url)
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(LiveDataCallAdapterFactory())
                    .build()
        }
    }

    class ApiProvider(private val retrofit: Retrofit) {

    }
}