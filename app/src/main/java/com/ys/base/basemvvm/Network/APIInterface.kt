package com.ys.base.basemvvm.Network

import com.ys.base.basemvvm.Model.GithubModel
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

/**
 * API Interface
 *
 * add your api
 *
 * 2way - call method
 * 1. Call(Retrofit Call)
 * 2. Observable/Single (RxJava Stream)
 *
 */
interface APIInterface {
//    val CLIENT_ID: String = "S000001"
//    val GET_APP_INIT_CODE: String = "api/v1/$CLIENT_ID/appInitCode/A0000"

    @Headers("Accept: application/vnd.github.nightshade-preview+json")
    @GET("/user/repos")
    fun getGithubSample(): Single<GithubModel>

    @GET("api/unknown")
    fun doGetListResources(): Call<String>

    @GET("w/api.php?action=query&list=search&srsearch=Albert%20Einstein&utf8=")
    fun getTest(): Observable<String>

}