package com.xrest.finalassignment.Adapter


import UserRepo
import android.app.Activity
import android.app.Dialog

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kiran.student.api.ServiceBuilder
import com.sushil.onlinefoodorder.R
import com.xrest.finalassignment.Class.Person
import com.xrest.finalassignment.Models.User



import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class UserAdapter(var lst:MutableList<Person>, var context: Context):RecyclerView.Adapter<UserAdapter.UserHolder>() {

    class UserHolder(view: View):RecyclerView.ViewHolder(view){

        var fullname:TextView
        var username:TextView
        var gender:TextView
        var img: ImageView
        var delete:ImageButton
        var update:ImageButton
        init{
            fullname = view.findViewById(R.id.fullname)
            username = view.findViewById(R.id.username)
            gender = view.findViewById(R.id.gender)
            img = view.findViewById(R.id.prof)
            delete = view.findViewById(R.id.delete)
            update = view.findViewById(R.id.update)
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
     val view = LayoutInflater.from(context).inflate(R.layout.user_show,parent,false)
        return  UserHolder(view)
    }

    override fun getItemCount(): Int {
       return lst.size
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {

        var current = lst[position]

        holder.fullname.text = current.FirstName
        holder.username.text = current.Username
        holder.gender.text = current.Gender
        Glide.with(context).load("${ServiceBuilder.BASE_URL}images/${current.Profile}").into(holder.img)

        holder.delete.setOnClickListener(){

            CoroutineScope(Dispatchers.IO).launch {

                val ur = UserRepo()
                val response= ur.delete(current._id.toString())

                lst.removeAt(position)
if(response.success==true)
{
    withContext(Dispatchers.Main){
        notifyDataSetChanged()
        Toast.makeText(context, "User Deleted", Toast.LENGTH_SHORT).show()
    }
}
                else{ withContext(Dispatchers.Main){
    Toast.makeText(context, "Gadbad Vayo", Toast.LENGTH_SHORT).show()
}


                }




            }

        }

        holder.update.setOnClickListener(){


            var dialog = Dialog(context,R.style.ThemeOverlay_AppCompat_Dialog_Alert)
            dialog.setContentView(R.layout.alert_view)
            dialog.show()
            dialog.setCancelable(true)
            val gallery_code=0
            val camera_code=1
            var image:String?=null
            var etFname:EditText = dialog.findViewById(R.id.etFname)
            var etLname:EditText = dialog.findViewById(R.id.etLname)
            var etAddress:EditText = dialog.findViewById(R.id.etAddress)
            var etPhone :EditText = dialog.findViewById(R.id.etPhone)
            var etUsername:EditText = dialog.findViewById(R.id.etUsername)
            var etPassword:EditText = dialog.findViewById(R.id.etPassword)
            var etConfirmPassword:EditText = dialog.findViewById(R.id.etCPassword)
            var btnAddStudent:TextView = dialog.findViewById(R.id.btnAddStudent)
            var login:TextView = dialog.findViewById(R.id.login)
            var img:ImageView = dialog.findViewById(R.id.logo)
            val male : RadioButton = dialog.findViewById(R.id.male)
            val female : RadioButton = dialog.findViewById(R.id.female)
            val others : RadioButton = dialog.findViewById(R.id.others)
            var gender =""
            val type="Customer"

            etFname.setText(lst[position].FirstName)
            etLname.setText(lst[position].Lastname)
            etAddress.setText(lst[position].Address)
            etPhone.setText(lst[position].PhoneNumber)
            etPassword.setText(lst[position].Password)
            etUsername.setText(lst[position].Address)
            Glide.with(context).load(lst[position].Profile).into(img)
            when(lst[position].Gender){

                "Male"->male.isChecked=true
                "Female"->female.isChecked=true
                "Others"->others.isChecked=true
            }

            img.setOnClickListener(){



                }




         btnAddStudent.setOnClickListener(){
             if(male.isChecked)
             {
                 gender ="Male"

             }
             if(female.isChecked)
             {

                 gender ="Female"

             }
             if(others.isChecked){

                 gender ="others"
             }


             val user:User=User(fname = etFname.text.toString(),lname = etLname.text.toString(),gender=gender,address =etAddress.text.toString(),username=etUsername.text.toString(),password = etPassword.text.toString() )


lst[position].FirstName = etFname.text.toString()
             lst[position].Lastname=etLname.text.toString()
             lst[position].Address=etAddress.text.toString()
             lst[position].PhoneNumber=etPhone.text.toString()
             lst[position].Password=etPassword.text.toString()
             lst[position].Username=etUsername.text.toString()
             lst[position].Gender=gender

             CoroutineScope(Dispatchers.IO).launch {



                 try{


                     val ur = UserRepo()
                     val response = ur.update(current._id!!)
                     if(response.success==true)
                     {


                         withContext(Dispatchers.Main) {
                             notifyDataSetChanged()
                             Toast.makeText(context, "student data updated", Toast.LENGTH_SHORT)
                                     .show()


                         }



                     }


                 }
                 catch(ex:Exception){

                 }

                }

                notifyDataSetChanged()
                dialog.dismiss()


            }



        }




    }





}