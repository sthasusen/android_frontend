import com.kiran.student.api.ServiceBuilder
import com.sushil.onlinefoodorder.MyApiRequest
import com.xrest.finalassignment.ApiFood
import com.xrest.finalassignment.Models.User
import com.xrest.finalassignment.Response.*
import okhttp3.MultipartBody

class UserRepo:MyApiRequest() {


    val api = ServiceBuilder.buildService(ApiC::class.java)


    suspend fun Login(username:String, password:String): LoginResponse {
return myApiRequest {

    api.Login(username, password)
}

    }



    suspend fun register(user:User): InsertResponse
    {
        return myApiRequest {
            api.Register(user)
        }

    }
    suspend fun upload( id:String,body: MultipartBody.Part): ImageResponse {
        return myApiRequest {
            api.addPhoto(id,body)
        }
    }

    suspend fun  getData():GetDataResponse{
        return myApiRequest{
            api.getData()
        }
    }
    suspend fun delete(id:String): DeleteResponse {
        return myApiRequest {
            api.deleteUser(id)
        }
    }

    suspend fun update(id:String): DeleteResponse {
        return myApiRequest {
            api.updateUser(id)
        }
    }




}