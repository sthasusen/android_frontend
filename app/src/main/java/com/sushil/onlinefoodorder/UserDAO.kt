//package com.sushil.onlinefoodorder
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.Query
//import com.sushil.onlinefoodorder.Entities.Users
//
//
//@Dao
//interface UserDAO {
//    @Insert
//    suspend fun Insert(user: Users)
//
//    @Query("select * from Users where email = (:username) and password =(:password) ")
//    suspend fun login(username:String,password:String)
//}