package com.accenture.magicapp.model.data.remote

import com.accenture.magicapp.BuildConfig
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitService {


    private val BASE_URL = "https://api.magicthegathering.io/v1/"
    private var retrofit: Retrofit? = null

    private fun getRetrofit(): Retrofit? {
        if (retrofit == null) {

            // Configuração de parametros de conexão
            val httpClient = OkHttpClient.Builder()
            httpClient.readTimeout(30, TimeUnit.SECONDS)
            httpClient.connectTimeout(30, TimeUnit.SECONDS)
            httpClient.writeTimeout(30, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) {
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                httpClient.addInterceptor(httpLoggingInterceptor)
                httpClient.addNetworkInterceptor(StethoInterceptor())
            }
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
        }
        return retrofit
    }

    fun getApiService(): MagicApi? {
        return getRetrofit()!!.create(MagicApi::class.java)
    }

}