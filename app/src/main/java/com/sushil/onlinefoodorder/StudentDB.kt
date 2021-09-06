//package com.sushil.onlinefoodorder
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.sushil.onlinefoodorder.Entities.Users
//
//
//@Database(
//    entities = [Users::class],
//    version = 1,
//    exportSchema = false
//
//)
//abstract class StudentDB:RoomDatabase() {
//
//
//    abstract fun getUserDao():UserDAO
//
//
//
//    companion object{
//
//
//        private var instance :StudentDB?=null
//        fun getInstance(context:Context):StudentDB{
//            if(instance==null)
//            {
//                synchronized(StudentDB::class){
//
//                    instance = buildDatabase(context)
//                }
//
//            }
//
//            return instance!!
//        }
//
//        fun buildDatabase(context: Context):StudentDB{
//
//            return Room.databaseBuilder(context,StudentDB::class.java,"FoodDB").build()
//        }
//
//    }
//
//
//
//
//
//
//}