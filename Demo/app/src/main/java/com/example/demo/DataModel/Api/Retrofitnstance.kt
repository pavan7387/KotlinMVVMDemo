package com.example.demo.DataModel.Api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {

    companion object {

        private var retrofit: Retrofit? = null
        private val BASE_URL:String = "https://picsum.photos"

        val client: Retrofit
            get() {

                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                val client = OkHttpClient.Builder()
                    .addInterceptor(Interceptor { chain ->
                        val original = chain.request()
                        //header
                        val request = original.newBuilder()
                            .header("Content-Type", "application/json")
                            .method(original.method(), original.body())
                            .build()
                        return@Interceptor chain.proceed(request)
                    })
                    .addInterceptor(interceptor)
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(1000, TimeUnit.SECONDS)
                    .build()

                if (retrofit == null) {
                    retrofit = Retrofit.Builder()
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL)
                        .build()
                }
                return retrofit!!
            }
    }
}