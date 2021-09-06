package com.sushil.notificationwear




import com.xrest.finalassignment.Response.DeleteResponse
import com.xrest.finalassignment.Response.LoginResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface ApiFood {



    @FormUrlEncoded
    @POST("/login")
    suspend fun Login(@Field("username") username:String,
                      @Field("password") password:String):Response<LoginResponse>


@GET("/booking/show")
suspend fun getBooking(@Header("Authorization")token:String ):Response<BookingResponse>

@DELETE("/delete/{id}")
suspend fun deleteBooking(@Header("Authorization")token:String,@Path("id")id:String?):Response<DeleteResponse>
@PUT("/updateBooking/{bid}")
    suspend fun update(@Header("Authorization")token:String,@Path("bid")id:String?,@Body qty:books):Response<DeleteResponse>




}