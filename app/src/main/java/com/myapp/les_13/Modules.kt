package com.myapp.les_13

import com.myapp.les_13.network.ResultCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    viewModelOf(::MainViewModel)
    single { createWebService(UserService::class.java, url = "https://6533daf1e1b6f4c590465308.mockapi.io") }
}

inline fun <reified T> createWebService(
    service: Class<T>,
    url: String
): T? {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    if (BuildConfig.DEBUG)
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor).build()

    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addCallAdapterFactory(ResultCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(service)
}
