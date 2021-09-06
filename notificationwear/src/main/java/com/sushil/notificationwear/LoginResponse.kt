package com.xrest.finalassignment.Response

import Person


data class LoginResponse (
    val status:Boolean?=null,
    val token: String?=null,
    val data: Person?=null

        ){
}