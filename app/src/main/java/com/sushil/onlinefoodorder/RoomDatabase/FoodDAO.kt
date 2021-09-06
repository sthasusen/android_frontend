//package com.xrest.finalassignment.RoomDatabase
//
//import androidx.room.*
//import com.sushil.onlinefoodorder.Class.Food
//import com.xrest.finalassignment.Class.Person
//
//@Dao
//interface FoodDAO {
//    @Insert
//    suspend fun InsertFood(food: Food)
//
//    @Delete
//    suspend fun delete(user: Food)
//
//    @Update
//    suspend fun update(user:Food)
//
//    @Query("SELECT * FROM Food")
//    suspend fun loadAllFood():MutableList<Food>
//
//
//    @Query("Delete  FROM Food")
//    suspend fun delete()
//
//
//}
