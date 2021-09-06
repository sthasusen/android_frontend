package com.sushil.onlinefoodorder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sushil.onlinefoodorder.Class.Food
import com.xrest.finalassignment.Adapter.FoodAdapter
import com.xrest.finalassignment.FoodRepo


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FoodShowFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view =inflater.inflate(R.layout.fragment_food_show, container, false)
        var lst:MutableList<Food> = mutableListOf()
        CoroutineScope(Dispatchers.IO).launch {


            val ur = FoodRepo()

            val response = ur.getFood()
            var list:MutableList<Food> = mutableListOf()
            list = response.data!!
//            lst = UserDB.getInstance(container!!.context).getFoodDAO().loadAllFood()

//
//            if(list.size>lst.size)
//            {
//                lst.clear()
//                lst = list as MutableList<Food>
//
//            }







            withContext(Dispatchers.Main){
                val rv:RecyclerView = view.findViewById(R.id.rv)
                val adapter = FoodAdapter(response.data!!,container!!.context)

                rv.layoutManager = LinearLayoutManager(container!!.context)
                rv.adapter = adapter


            }

        }

        return view
    }
}






