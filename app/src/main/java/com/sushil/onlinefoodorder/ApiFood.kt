package com.xrest.finalassignment




import com.sushil.onlinefoodorder.BookingResponse
import com.sushil.onlinefoodorder.Class.Food
import com.sushil.onlinefoodorder.Class.books
import com.sushil.onlinefoodorder.Response.FoodResponse
import com.sushil.onlinefoodorder.Response.FoodResponses
import com.xrest.finalassignment.Response.DeleteResponse

import com.xrest.finalassignment.Response.InsertResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface ApiFood {


    @POST("/food/insert")
    suspend fun insertFood(@Body food: Food):Response<FoodResponses>

    @GET("/food/show")
    suspend fun getFood():Response<FoodResponse>
    @Multipart
    @PUT("/food/photo/{id}")
    suspend fun foods(
        @Path("id") id: String,
        @Part file: MultipartBody.Part
    ):Response<InsertResponse>



    @POST("/booking/{pid}")
suspend fun addToCart(@Path("pid") id:String,@Header("Authorization") token:String,@Body Qty:books):Response<DeleteResponse>

@GET("/booking/show")
suspend fun getBooking(@Header("Authorization")token:String ):Response<BookingResponse>

@DELETE("/delete/{id}")
suspend fun deleteBooking(@Header("Authorization")token:String,@Path("id")id:String?):Response<DeleteResponse>
@PUT("/updateBooking/{bid}")
    suspend fun update(@Header("Authorization")token:String,@Path("bid")id:String?,@Body qty:books):Response<DeleteResponse>
    @GET("/search/{name}")
    suspend fun search(@Path("name") name:String?):Response<FoodResponse>




}