package com.sushil.onlinefoodorder.Class

import com.xrest.finalassignment.Class.Person

data class Booking(
    val _id:String?=null,
    val UserId:Person?=null,
    val Qty:Int?=null,
    val ProductId:Food?=null,
    val Date:String?=null


)

data class books(val Qty:Int?=null)