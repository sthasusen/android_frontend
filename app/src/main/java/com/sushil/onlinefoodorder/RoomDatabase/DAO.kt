//package com.xrest.finalassignment.RoomDatabase
//
//
//import com.xrest.finalassignment.Class.Person
//import com.xrest.finalassignment.Models.User
//import retrofit2.http.GET
//
//@Dao
//interface DAO {
//
//
//
//    @Insert
//    suspend fun InsertUser(user: Person)
//
//    @Delete
//    suspend fun delete(user:Person)
//
//    @Update
//    suspend fun update(user:Person)
//
//    @Query("SELECT * FROM Person")
//    suspend fun loadAllUsers():MutableList<Person>
//
//
//}