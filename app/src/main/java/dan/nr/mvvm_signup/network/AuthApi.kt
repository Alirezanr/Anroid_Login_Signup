package dan.nr.mvvm_signup.network

import dan.nr.mvvm_signup.model.responses.auth.LoginResponse
import dan.nr.mvvm_signup.model.responses.auth.RegistrationResponse
import retrofit2.http.*

interface AuthApi
{
    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(@Field("email") email: String, @Field("password") password: String): LoginResponse

    @FormUrlEncoded
    @POST("auth/signup")
    suspend fun signup(@Field("email") email: String,
                       @Field("name") name: String,
                       @Field("password") password: String,
                       @Field("password_confirmation") password_confirmation: String): RegistrationResponse
}