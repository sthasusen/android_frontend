package com.sushil.onlinefoodorder

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sushil.onlinefoodorder.Adapter.BookingAdapter
import com.sushil.onlinefoodorder.Class.Booking
import com.xrest.finalassignment.FoodRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookingShow : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_show)
        val rv:RecyclerView = findViewById(R.id.rvs)
var lst:MutableList<Booking> = mutableListOf()
        CoroutineScope(Dispatchers.IO).launch{
            val repo = FoodRepo()
val response = repo.getBook()
            lst = response.data!!
            withContext(Main){

                val adapter = BookingAdapter(this@BookingShow,lst)
                rv.layoutManager =LinearLayoutManager(this@BookingShow)
                rv.adapter =adapter

            }



        }



    }
}