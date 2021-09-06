package com.xrest.finalassignment




import com.kiran.student.api.ServiceBuilder
import com.sushil.onlinefoodorder.BookingResponse
import com.sushil.onlinefoodorder.Class.Food
import com.sushil.onlinefoodorder.Class.books
import com.sushil.onlinefoodorder.MyApiRequest
import com.sushil.onlinefoodorder.Response.FoodResponse
import com.sushil.onlinefoodorder.Response.FoodResponses
import com.xrest.finalassignment.Response.DeleteResponse

import com.xrest.finalassignment.Response.InsertResponse
import okhttp3.MultipartBody

class FoodRepo:MyApiRequest() {



    val api = ServiceBuilder.buildService(ApiFood::class.java)



    suspend fun  insertFood(food: Food): FoodResponses {
        return myApiRequest {
            api.insertFood(food)
        }
    }
    suspend fun getFood(): FoodResponse {
        return myApiRequest {
            api.getFood()
        }
    }

    suspend fun addToCart(pid:String,Qty:books):DeleteResponse{

        return myApiRequest {
            api.addToCart(pid,ServiceBuilder.token!!,Qty)
        }

    }

    suspend fun search(name:String?):FoodResponse{
        return myApiRequest {
            api.search(name)
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

    suspend fun addPhoto(id:String,body:MultipartBody.Part):InsertResponse{
        return myApiRequest {

api.foods(id,body)
        }
    }




}