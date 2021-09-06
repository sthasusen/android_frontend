package com.sushil.onlinefoodorder

import Notification
import UserRepo
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.android.material.snackbar.Snackbar
import com.kiran.student.api.ServiceBuilder
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity(), View.OnClickListener,SensorEventListener {
    lateinit var etEmail: EditText
    lateinit var  etPassword: EditText
    lateinit var  login: Button
    lateinit var  signup: Button
    lateinit var  LinearLayout: LinearLayout
    val MIN_PASSWORD_LENGTH = 6
    var permissions = arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
var sensor:Sensor?=null
  private lateinit  var sensorManager :SensorManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewInitializations()
        if (!hasPermission()) {
            requestPermission()
        }



        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        if (!checkSensor())
            return
        else {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
        login.setOnClickListener(this)



    }
    private fun checkSensor(): Boolean {
        var flag = true
        if (sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) == null) {
            flag = false
        }
        return flag
    }
    fun viewInitializations() {
        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
        login = findViewById(R.id.bt_login)
        signup = findViewById(R.id.bt_signup)

        // To show back button in actionbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // Checking if the input in form is valid
    fun validateInput(): Boolean {
        if (etEmail.text.toString() == "") {
            etEmail.error = "Please Enter Email"
            return false
        }
        if (etPassword.text.toString() == "") {
            etPassword.error = "Please Enter Password"
            return false
        }

        // checking the proper email format
        if (!isEmailValid(etEmail.text.toString())) {
            etEmail.error = "Please Enter Valid Email"
            return false
        }

        // checking minimum password Length
        if (etPassword.text.length < MIN_PASSWORD_LENGTH) {
            etPassword.error = "Password Length must be more than " + MIN_PASSWORD_LENGTH + "characters"
            return false
        }
        return true
    }

    fun isEmailValid(email: String?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Hook Click Event
    fun performSignUp(v: View) {
        if (validateInput()) {

            // Input is valid, here send data to your server

            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
            // Here you can call you API
            // Check this tutorial to call server api through Google Volley Library https://handyopinion.com
        }
    }

    fun goToSignup(v: View) {
        // Open your SignUp Activity if the user wants to signup
        // Visit this article to get SignupActivity code https://handyopinion.com/signup-activity-in-android-studio-kotlin-java/
        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
    }

    override fun onClick(v: View?) {
        when(v?.id)
        {


            R.id.bt_login -> {


                val email = etEmail!!.text.toString()
                val password = etPassword!!.text.toString()
                CoroutineScope(Dispatchers.IO).launch {

                    try {
                        val repository = UserRepo()
                        val response = repository.Login(email, password)
                        if (response.status == true) {

                            //save to sharereference
                            ServiceBuilder.token = "Bearer " + response.token
                            ServiceBuilder.user = response.data!!
                            val sharedPref = getSharedPreferences("data", MODE_PRIVATE)
                            val editor = sharedPref.edit()
                            editor.putString("un", email)
                            editor.putString("pw", password)
                            editor.apply()


                            Notify()

                            startActivity(
                                    Intent(
                                            this@LoginActivity,
                                            DashboardActivity::class.java
                                    )
                            )

                        } else {

                            withContext(Main) {
                                Toast.makeText(this@LoginActivity, "Wrong Username or Password", Toast.LENGTH_SHORT).show()
                            }
                        }

                    } catch (ex: Exception) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                    this@LoginActivity,
                                    "Login error", Toast.LENGTH_SHORT
                            ).show()

                        }
                    }

                }
            }
            R.id.bt_signup->{
                startActivity(Intent(this@LoginActivity,SignupActivity::class.java))
            }


        }
    }
    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this@LoginActivity,
            permissions, 1
        )
    }

    private fun hasPermission(): Boolean {
        var hasPermission = true
        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                hasPermission = false
            }
        }
        return hasPermission
    }

    override fun onSensorChanged(event: SensorEvent?) {
val values =event!!.values[0]
        if(values<-2){
            startActivity(Intent(this@LoginActivity,SignupActivity::class.java))
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
    fun Notify(){

        val notificationManager= NotificationManagerCompat.from(this)

        val notificationChannels  = Notification(this)
        notificationChannels.createNotification()
        val notification = NotificationCompat.Builder(this,notificationChannels.c1)

                .setSmallIcon(R.drawable.ic_baseline_emoji_food_beverage_24)
                .setContentTitle("Welcome")
                .setContentTitle("Login Success").setColor(Color.BLUE).build()
        notificationManager.notify(1,notification)
    }

}