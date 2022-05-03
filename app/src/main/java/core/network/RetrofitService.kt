package core.network

import com.example.naviassignment.features.closedPullRequests.data.api.GithubApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService private constructor() {

    companion object {

        private var githubApiService: GithubApi? = null

        fun getRetrofitInstance(): GithubApi {
            if (githubApiService == null) {
                synchronized(this) {
                    if (githubApiService == null) {
                        githubApiService = createRetrofitService().create(GithubApi::class.java)
                    }
                }
            }

            return githubApiService!!
        }

        private fun createRetrofitService(): Retrofit {
            val interceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            val build = OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(build)
                .build()
        }
    }
}