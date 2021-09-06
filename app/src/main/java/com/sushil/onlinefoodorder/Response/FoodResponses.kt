package com.sushil.onlinefoodorder.Response

import com.sushil.onlinefoodorder.Class.Food

data class FoodResponses(
    val success:Boolean?=null,
    val data: Food?=null
)