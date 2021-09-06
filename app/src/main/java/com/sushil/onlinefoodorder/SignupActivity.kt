package com.sushil.onlinefoodorder

import UserRepo
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Patterns
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.sushil.onlinefoodorder.Models.Users
import com.xrest.finalassignment.Models.User

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class SignupActivity : AppCompatActivity() {

    lateinit var etFirstName: EditText
    lateinit var etLastName:EditText
    lateinit var etEmail: EditText
    lateinit var male: RadioButton
    lateinit var female:RadioButton
    lateinit var others: RadioButton
    lateinit var img: ImageView

    lateinit var etUsername: EditText
    lateinit var etPhone: EditText
    lateinit var etAddress: EditText
    lateinit var etPassword:EditText
    lateinit var etRepeatPassword:EditText
    lateinit var register:Button
    lateinit var register2:Button
    val MIN_PASSWORD_LENGTH = 6;

    private val gallery_code=0
    private val camera_code=1
    var image:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
 viewInitializations()

        register.setOnClickListener(){
            var gender =""
            if(male.isChecked)
            {
                gender ="Male"
            }
            if(female.isChecked)
            {
                gender ="Female"
            }
            if(others.isChecked)
            {
                gender ="Others"
            }
            val firstName = etFirstName.text.toString()
            val lastName = etLastName.text.toString()
            val email = etEmail.text.toString()
            val username = etUsername.text.toString()
            val address = etAddress.text.toString()
            val phone = etPhone.text.toString()
            val password = etPassword.text.toString()
            val repeatPassword = etRepeatPassword.text.toString()

            if(password!=repeatPassword)
            {
                Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show()
            }
            else{
                val user = User(fname =firstName,lname=lastName,address=address,phone=phone,username=username,password=password,gender = gender,type = "Customer")

                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val userRepository = UserRepo()
                        val response = userRepository.register(user)

                        if (response.status == true) {
                            withContext(Main) {
                                if(image!=null)
                                {
                                    uploadImage(response.data!!._id!!,image!!)

                                }
                                startActivity(Intent(this@SignupActivity,LoginActivity::class.java))
                                Toast.makeText(
                                    this@SignupActivity,
                                    "Register bhayo", Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    } catch (ex: Exception) {
                        withContext(Main) {
                            Toast.makeText(
                                this@SignupActivity,
                                "$ex Username cannot be duplicate", Toast.LENGTH_SHORT
                            ).show()


                        }
                    }
                }
            }
              }

        img.setOnClickListener(){
            val popup = PopupMenu(this,img)
            popup.menuInflater.inflate(R.menu.gallery_camera,popup.menu)
            popup.setOnMenuItemClickListener { item->
                when(item.itemId){
                    R.id.menuCamera->
                        openCamera()
                    R.id.menuGallery->{
                        openGallery()
                    }


                }

                true
            }
            popup.show()
        }


        register2.setOnClickListener(){
            Toast.makeText(this@SignupActivity, "asdasdasdas", Toast.LENGTH_SHORT).show()
           startActivity(Intent(this@SignupActivity,LoginActivity::class.java))

        }



//                CoroutineScope(Dispatchers.IO).launch {
//                    val user = Users(firstName,lastName,email,password,"")
//                    StudentDB.getInstance(this@SignupActivity).getUserDao().Insert(user)
//                    withContext(Dispatchers.Main)
//                    {
//                        Toast.makeText(this@SignupActivity, "User Registered", Toast.LENGTH_SHORT).show()
//                    }
//
//
//
//                }


            }

    fun openGallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type="image/*"
        startActivityForResult(intent,gallery_code)

    }

    fun openCamera() {

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent,camera_code)


    }

    fun bitmapToFile(
            bitmap: Bitmap,
            fileNameToSave: String
    ): File? {
        var file: File? = null
        return try {
            file = File(
                    this@SignupActivity.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                            .toString() + File.separator + fileNameToSave
            )
            file?.createNewFile()
            //Convert bitmap to byte array
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos) // YOU can also save it in JPEG
            val bitMapData = bos.toByteArray()
            //write the bytes in file
            val fos = FileOutputStream(file)
            fos.write(bitMapData)
            fos.flush()
            fos.close()
            file
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            file // it will return null
        }
    }

    fun uploadImage(id: String,image:String) {
        if(image!=null)
        {

            var file = File(image!!)
            val extention = MimeTypeMap.getFileExtensionFromUrl(image)
            val mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extention)
            var reqFile = RequestBody.create(MediaType.parse(mimeType), file)


            var body = MultipartBody.Part.createFormData("file",file.name,reqFile)
            CoroutineScope(Dispatchers.IO).launch {
                try{
                    val ur = UserRepo()
                    val response = ur.upload(id,body)
                    if(response.success==true)
                    {

                        withContext(Dispatchers.Main){
                            Toast.makeText(this@SignupActivity, "Add in Room databse", Toast.LENGTH_SHORT).show()
                        }

                    }

                }
                catch(ex:Exception)
                {

                }



            }

        }
    }

    fun viewInitializations() {
        etFirstName = findViewById(R.id.etFname)
        etLastName = findViewById(R.id.etLname)
        etUsername = findViewById(R.id.etUsername)
        etPhone = findViewById(R.id.etPhone)
        etAddress = findViewById(R.id.etAddress)
        male = findViewById(R.id.Male)
        female = findViewById(R.id.Female)
        others = findViewById(R.id.Others)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etRepeatPassword = findViewById(R.id.etCPassword)
        register= findViewById(R.id.btnReg)
        register2= findViewById(R.id.btnLogin)
img = findViewById(R.id.imagess)
        // To show back button in actionbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // Checking if the input in form is valid
    fun validateInput(): Boolean {
        if (etFirstName.text.toString().equals("")) {
            etFirstName.setError("Please Enter First Name")
            return false
        }
        if (etLastName.text.toString().equals("")) {
            etLastName.setError("Please Enter Last Name")
            return false
        }
        if (etEmail.text.toString().equals("")) {
            etEmail.setError("Please Enter Email")
            return false
        }
        if (etPassword.text.toString().equals("")) {
            etPassword.setError("Please Enter Password")
            return false
        }
        if (etRepeatPassword.text.toString().equals("")) {
            etRepeatPassword.setError("Please Enter Repeat Password")
            return false
        }

        // checking the proper email format
        if (!isEmailValid(etEmail.text.toString())) {
            etEmail.setError("Please Enter Valid Email")
            return false
        }

        // checking minimum password Length
        if (etPassword.text.length < MIN_PASSWORD_LENGTH) {
            etPassword.setError("Password Length must be more than " + MIN_PASSWORD_LENGTH + "characters")
            return false
        }

        // Checking if repeat password is same
        if (!etPassword.text.toString().equals(etRepeatPassword.text.toString())) {
            etRepeatPassword.setError("Password does not match")
            return false
        }
        return true
    }

    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Hook Click Event

    fun performSignUp (view: View) {
        if (validateInput()) {

            // Input is valid, here send data to your server


            Toast.makeText(this,"Login Success",Toast.LENGTH_SHORT).show()
            // Here you can call you API

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == gallery_code && data != null) {
                val selectedImage = data.data
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                val contentResolver = contentResolver
                val cursor =
                        contentResolver.query(selectedImage!!, filePathColumn, null, null, null)
                cursor!!.moveToFirst()
                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                image = cursor.getString(columnIndex)
                img.setImageBitmap(BitmapFactory.decodeFile(image))
                cursor.close()
            } else if (requestCode == camera_code && data != null) {
                val imageBitmap = data.extras?.get("data") as Bitmap
                val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                val file = bitmapToFile(imageBitmap, "$timeStamp.jpg")
                image = file!!.absolutePath
                img.setImageBitmap(BitmapFactory.decodeFile(image))
            }
        }
    }

}