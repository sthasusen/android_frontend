package com.sushil.notificationwear






import com.xrest.finalassignment.Response.DeleteResponse
import com.xrest.finalassignment.Response.LoginResponse

import okhttp3.MultipartBody

class FoodRepo:MyApiRequest() {



    val api = ServiceBuilder.buildService(ApiFood::class.java)



    suspend fun Login(username:String, password:String): LoginResponse {
        return myApiRequest {

            api.Login(username, password)
        }

    }



    suspend fun getBook():BookingResponse{
        return myApiRequest {
            api.getBooking(ServiceBuilder.token!!)
        }
    }



    suspend fun delete(id:String):DeleteResponse{
        return myApiRequest {
            api.deleteBooking(ServiceBuilder.token!!,id)
        }
    }

    suspend fun update(id:String,qty: books):DeleteResponse{
        return myApiRequest {
            api.update(ServiceBuilder.token!!,id,qty)
        }
    }





}