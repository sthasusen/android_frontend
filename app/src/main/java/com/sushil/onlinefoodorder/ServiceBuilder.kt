package com.kiran.student.api

import com.xrest.finalassignment.Class.Person
import com.xrest.finalassignment.Models.User
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object      ServiceBuilder {

//     val BASE_URL= "http://10.0.2.2:3000/"
val BASE_URL= "http://10.0.2.2:3000/"
    var token: String? = null
var user: Person?=null
  val okHttp = OkHttpClient.Builder()

    private val retrofitBuilder =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build())

    private val retrofit = retrofitBuilder.build()
    //Generic function
    fun <T> buildService(serviceType: Class<T>): T{

        return retrofit.create(serviceType)
    }


}