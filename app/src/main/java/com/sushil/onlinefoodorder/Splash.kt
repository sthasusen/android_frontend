package com.sushil.onlinefoodorder

import UserRepo
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.kiran.student.api.ServiceBuilder
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val sharedPref = getSharedPreferences("data", MODE_PRIVATE)
        val username = sharedPref.getString("un", "").toString()
        val password = sharedPref.getString("pw", "").toString()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                delay(5000)
                val repo = UserRepo()

                val re = UserRepo().Login(username, password)
                if (re.status == true) {
                    ServiceBuilder.token = "Bearer ${re.token!!}"
                    ServiceBuilder.user = re.data

                    startActivity(Intent(this@Splash, DashboardActivity::class.java))


                } else {
                    startActivity(Intent(this@Splash, LoginActivity::class.java))

                }

            }
            catch (ex:Exception){
                withContext(Main){
                    Toast.makeText(this@Splash, "Server Not Found", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}