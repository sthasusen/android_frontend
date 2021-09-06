import com.xrest.finalassignment.Models.User
import com.xrest.finalassignment.Response.*
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface ApiC{



    @POST("/insert")
    suspend fun Register(@Body user:User):Response<InsertResponse>

    @FormUrlEncoded
    @POST("/login")
    suspend fun Login(@Field("username") username:String,
    @Field("password") password:String):Response<LoginResponse>

@GET("/show")
suspend fun getData():Response<GetDataResponse>

    @Multipart
    @PUT("/photo/{id}")
    suspend fun addPhoto(
            @Path("id") id: String,
            @Part file: MultipartBody.Part
    ):Response<ImageResponse>

    @DELETE("/delete/{id}")
    suspend fun deleteUser(@Path("id") id:String):Response<DeleteResponse>
    @PUT("/update/{id}")
    suspend fun updateUser(@Path("id") id:String):Response<DeleteResponse>

}