package com.sushil.notificationwear

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val rv: RecyclerView = findViewById(R.id.rvs)
        var lst:MutableList<Booking> = mutableListOf()
        CoroutineScope(Dispatchers.IO).launch{
            val repo = FoodRepo()
            val response = repo.getBook()
            lst = response.data!!
            withContext(Dispatchers.Main){

                val adapter = BookingAdapter(this@MainActivity2,lst)
                rv.layoutManager = LinearLayoutManager(this@MainActivity2)
                rv.adapter =adapter

            }



        }
    }
}