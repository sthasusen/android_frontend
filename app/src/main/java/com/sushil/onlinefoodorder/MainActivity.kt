package com.sushil.onlinefoodorder

import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kiran.student.api.ServiceBuilder
import com.sushil.onlinefoodorder.Class.Food
import com.xrest.finalassignment.Adapter.FoodAdapter
import com.xrest.finalassignment.FoodRepo

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() ,SensorEventListener{
    var sensor:Sensor?=null
    var sensorManager:SensorManager?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val search:EditText= findViewById(R.id.search)
        val btn : Button = findViewById(R.id.btnSearch)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        if(!checkSensor()){
            return
        }
        else{
            sensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)
            sensorManager!!.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }

        var lst:MutableList<Food> = mutableListOf()

        CoroutineScope(Dispatchers.IO).launch {


            val ur = FoodRepo()

            val response = ur.getFood()
            var list:MutableList<Food> = mutableListOf()
            list = response.data!!

            withContext(Dispatchers.Main){
currentAdapter(response.data!!)


            }

        }


btn.setOnClickListener(){
    CoroutineScope(Dispatchers.IO).launch{
        val repo = FoodRepo()
        val response = repo.search(search.text.toString())
        withContext(Dispatchers.Main) {
            currentAdapter(response.data!!)
        }



    }
}

    }
    fun currentAdapter(lst:MutableList<Food>){
        val rv: RecyclerView = findViewById(R.id.rv)
        val adapter = FoodAdapter(lst,this@MainActivity)

        rv.layoutManager = LinearLayoutManager(this@MainActivity)
        rv.adapter = adapter
    }
    private fun checkSensor(): Boolean {
        var flag = true
        if (sensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT) == null) {
            flag = false
        }
        return flag
    }
   override fun onSensorChanged(event: SensorEvent?){

       val values = event!!.values[0]


       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
           if (!Settings.System.canWrite(this)) {
               val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
               intent.data = Uri.parse("package:" + getPackageName())
               intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
               startActivity(intent)
           } else {


               Settings.System.putInt(
                       contentResolver,
                       Settings.System.SCREEN_BRIGHTNESS, 0
               )
               if(values>15000f) {
                   Toast.makeText(this, "15000", Toast.LENGTH_SHORT).show()
                   Settings.System.putInt(
                           contentResolver,
                           Settings.System.SCREEN_BRIGHTNESS, 80
                   )
               }
               else if(values> 30000f){
                   Toast.makeText(this, "30000", Toast.LENGTH_SHORT).show()

                   Settings.System.putInt(
                           contentResolver,
                           Settings.System.SCREEN_BRIGHTNESS, 190
                   )
               }
               else if(values== 40000f){
                   Toast.makeText(this, "40000", Toast.LENGTH_SHORT).show()

                   Settings.System.putInt(
                           contentResolver,
                           Settings.System.SCREEN_BRIGHTNESS, 255
                   )
               }










           }
       }

   }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

}