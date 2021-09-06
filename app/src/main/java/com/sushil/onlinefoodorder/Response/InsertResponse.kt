package com.xrest.finalassignment.Response

import com.xrest.finalassignment.Models.User

data class InsertResponse(
                     val status:Boolean?=null,
                     val data: User?=null

) {
}