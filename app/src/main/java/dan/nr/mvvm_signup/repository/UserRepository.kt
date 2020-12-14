package dan.nr.mvvm_signup.repository

import dan.nr.mvvm_signup.network.AuthApi
import dan.nr.mvvm_signup.network.UserApi
import dan.nr.mvvm_signup.utils.UserPreferences

class UserRepository(private val api: UserApi) : BaseRepository()
{
    suspend fun getUser()=safeApiCall {
        api.getUser()
    }
}