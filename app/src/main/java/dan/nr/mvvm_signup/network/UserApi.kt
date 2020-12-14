package dan.nr.mvvm_signup.network

import dan.nr.mvvm_signup.model.responses.auth.LoginResponse
import retrofit2.http.GET

interface UserApi
{
    @GET("user")
    suspend fun getUser():LoginResponse
}