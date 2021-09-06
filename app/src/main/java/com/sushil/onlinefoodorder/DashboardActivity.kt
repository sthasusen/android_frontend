package com.sushil.onlinefoodorder

import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.kiran.student.api.ServiceBuilder

class DashboardActivity : AppCompatActivity(), View.OnClickListener,SensorEventListener {


    private lateinit var sensor: Sensor
    private lateinit var sensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        if (!checkSensor())
            return
        else {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }




        val food:CardView = findViewById(R.id.food)
        val cart:CardView = findViewById(R.id.booking)
        val profile :CardView = findViewById(R.id.profile)

        val location :CardView = findViewById(R.id.location)

        val add :CardView = findViewById(R.id.addFood)

        val log :CardView = findViewById(R.id.logout)


        if(ServiceBuilder.user!!.UserType!="Admin")
        {
            add.setOnClickListener(){
                Toast.makeText(this, "Only Admin is Allowed", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            add.setOnClickListener(this)
        }

        food.setOnClickListener(this)
        cart.setOnClickListener(this)
        profile.setOnClickListener(this)



        location.setOnClickListener(this)
        log.setOnClickListener(this)




    }
    private fun checkSensor(): Boolean {
        var flag = true
        if (sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) == null) {
            flag = false
        }
        return flag
    }
    override fun onClick(v: View?) {
        when(v?.id){

            R.id.food->{
                startActivity(Intent(this@DashboardActivity,MainActivity::class.java))
            }
            R.id.booking->{
                startActivity(Intent(this@DashboardActivity,BookingShow::class.java))
            }
            R.id.profile->{
                startActivity(Intent(this@DashboardActivity,ProfileActivity::class.java))

            }
            R.id.location->{
                startActivity(Intent(this@DashboardActivity,MapsActivity::class.java))

            }
            R.id.addFood->{
                startActivity(Intent(this@DashboardActivity,AddFood::class.java))

            }
            R.id.logout->{
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
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val values = event!!.values[0]
        if(values<=4) {

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

        else
    {

    }
    }



    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}