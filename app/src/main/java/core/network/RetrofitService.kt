package core.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService private constructor(){

    companion object {

        private var retrofitService: Retrofit? = null

        fun getRetrofitInstance() : Retrofit {
            if(retrofitService == null) {
                synchronized(this) {
                    if (retrofitService == null) {
                        retrofitService = createRetrofitService()
                    }
                }
            }

            return retrofitService!!
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
                .baseUrl("ss")
                .addConverterFactory(GsonConverterFactory.create())
                .client(build)
                .build()
        }

        fun <T> Retrofit.getGithubApiService (className: T) : T {
            return createRetrofitService().create(className!!::class.java)
        }
    }
}