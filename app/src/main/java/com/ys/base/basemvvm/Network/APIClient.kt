package com.ys.base.basemvvm.Network

import com.facebook.stetho.okhttp3.StethoInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory


/**
 * Create Retrofit using Retrofit.Builder()
 *
 * 1. set BASE_URL
 * 2. add ConverterFactory & add CallAdapterFactory
 * 3. add httpLoggingInterceptor
 *
 * how to use:
 * 1. change to BASE_URL
 * 2. write APIInterface
 * 2. using - val apiInterface = APIClient.getClient().create(APIInterface::class.java)
 */
class APIClient {

    companion object {
//        val BASE_URL = "https://api.github.com/"
        val BASE_URL = "https://en.wikipedia.org/"


        lateinit var retrofit: Retrofit

        fun getClient() : Retrofit {

            // logging-interceptor는 반환된 모든 응답에 대해 로그 문자열을 생성합니다
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder().run {
                addInterceptor(interceptor)
                addNetworkInterceptor(StethoInterceptor())
                build()
            }

            retrofit = Retrofit.Builder().run {
                baseUrl(BASE_URL)
                addConverterFactory(GsonConverterFactory.create())
                // RxJava2 call adapter
                addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                client(client)
                build()
            }



//            val client = OkHttpClient.Builder()
//                    .addInterceptor(interceptor)
//                    .addNetworkInterceptor(StethoInterceptor())
//                    .build()
//
//
//            retrofit = Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    // RxJava2 call adapter
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    .client(client)
//                    .build()

            return retrofit
        }
    }
}