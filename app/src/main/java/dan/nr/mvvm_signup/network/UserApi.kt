package dan.nr.mvvm_signup.network

import dan.nr.mvvm_signup.model.responses.auth.LoginResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi
{
    @GET("user")
    suspend fun getUser():LoginResponse

    @POST("logout")
    suspend fun userLogout():ResponseBody
}