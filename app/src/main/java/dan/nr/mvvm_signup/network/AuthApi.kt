package dan.nr.mvvm_signup.network

import dan.nr.mvvm_signup.model.responses.auth.LoginResponse
import retrofit2.http.*

interface AuthApi
{
    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(@Field("email") email: String,
                      @Field("password") password: String
                     ): LoginResponse
}