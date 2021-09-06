package com.sushil.onlinefoodorder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.bumptech.glide.Glide
import com.kiran.student.api.ServiceBuilder
import de.hdodenhof.circleimageview.CircleImageView

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val user = ServiceBuilder.user!!
        val fname: TextView = findViewById(R.id.fname)
        val lname: TextView = findViewById(R.id.lname)
        val phone: TextView = findViewById(R.id.phone)
        val update: TextView = findViewById(R.id.update)
        val username: TextView = findViewById(R.id.username)
        val img: CircleImageView = findViewById(R.id.profile)


        fname.text = user.FirstName
        lname.text = user.Lastname
        username.text= user.Username
        phone.text = user.PhoneNumber
update.setOnClickListener(){
    ServiceBuilder.token = null
    ServiceBuilder.user = null
    val prefs = getSharedPreferences("data", MODE_PRIVATE);
    val editor = prefs?.edit()
    if (editor != null) {
        editor.clear()
        editor.apply()
    }
    val intent = Intent(this, LoginActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP;
    startActivity(intent);
    finish();


}

        if(user.Profile=="no-img.jpg"){
            Glide.with(this).load(R.drawable.ic_person).into(img)

        }
        else{

            Glide.with(this).load("${ServiceBuilder.BASE_URL}images/${user.Profile}").into(img)
        }
    }
}