package com.sushil.notificationwear

import android.content.Intent
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : WearableActivity() {
    lateinit var etEmail: EditText
    lateinit var  etPassword: EditText

    lateinit var  login: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login = findViewById(R.id.bt_login)

        login.setOnClickListener(){
            etEmail = findViewById(R.id.et_email)
            etPassword = findViewById(R.id.et_password)
            val email = etEmail!!.text.toString()
            val password = etPassword!!.text.toString()
            CoroutineScope(Dispatchers.IO).launch {

                try {
                    val repository = FoodRepo()
                    val response = repository.Login(email, password)
                    if (response.status == true) {

                        ServiceBuilder.token="Bearer "+response.token!!
startActivity(
                            Intent(
                                this@MainActivity,
                                MainActivity2::class.java
                            )
                        )

                    } else {

                    }

                } catch (ex: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@MainActivity,
                            "Login error", Toast.LENGTH_SHORT
                        ).show()

                    }
                }

            }

        }

        setAmbientEnabled()
    }
}