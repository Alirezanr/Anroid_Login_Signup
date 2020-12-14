package dan.nr.mvvm_signup.repository

import dan.nr.mvvm_signup.network.AuthApi
import dan.nr.mvvm_signup.utils.UserPreferences

class AuthRepository(private val api: AuthApi,
                     private val preferences: UserPreferences) : BaseRepository()
{

    suspend fun login(email: String, password: String) = safeApiCall {
        api.login(email, password)
    }

    suspend fun saveAuthToken(token:String)
    {
        preferences.saveAuthToken(token)
    }
}