package com.xrest.finalassignment.Adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kiran.student.api.ServiceBuilder
import com.sushil.onlinefoodorder.Class.Food
import com.sushil.onlinefoodorder.Class.books
import com.sushil.onlinefoodorder.R
import com.xrest.finalassignment.FoodRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FoodAdapter(val lst:MutableList<Food>, val context: Context):RecyclerView.Adapter<FoodAdapter.FoodHolder>() {


    class FoodHolder(view:View):RecyclerView.ViewHolder(view){

        var img:ImageView
        var name:TextView
        var desc:TextView
        var price:TextView
        var btn: TextView


        init{

            img = view.findViewById(R.id.img)
            name = view.findViewById(R.id.name)
            desc = view.findViewById(R.id.description)
            price = view.findViewById(R.id.price)
            btn = view.findViewById(R.id.book)


        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
       val view = LayoutInflater.from(context).inflate(R.layout.mero_food,parent,false)
        return FoodHolder(view)
    }

    override fun getItemCount(): Int {
       return lst.size
    }

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
       val food = lst[position]
        holder.name.text = food.Name
        holder.desc.text = food.Description
        holder.price.text = "Rs "+food.Price.toString()
        Glide.with(context).load("${ServiceBuilder.BASE_URL}images/${food.Image}").into(holder.img)




        holder.btn.setOnClickListener(){
dialog(food)
        }


    }


    fun dialog(food:Food){
        val d = AlertDialog.Builder(context)
        d.setTitle("Book Confirmation")

        d.setMessage("Are you sure you want to book this item?")
        d.setPositiveButton("Yes"){dialog,which->

book(food._id!!)
            Toast.makeText(context, "Added to Cart", Toast.LENGTH_SHORT).show()
            dialog.cancel()

        }
        d.setNegativeButton("No"){dialog, which ->

        }
        val alert = d.create()

        alert.setCancelable(true)
        alert.show()


    }
    fun book(pid:String){


        CoroutineScope(Dispatchers.IO).launch{
            val repo = FoodRepo()
            val response = repo.addToCart(pid, books(1))
            if(response.success==true){
                withContext(Main){
                    Toast.makeText(context, "Added To Cart", Toast.LENGTH_SHORT).show()
                }
            }






        }



    }
}