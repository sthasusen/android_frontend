
package com.sushil.onlinefoodorder.Response
import com.sushil.onlinefoodorder.Class.Food

data class FoodResponse(
        val success:Boolean?=null,
        val data:MutableList<Food>?=null
)

