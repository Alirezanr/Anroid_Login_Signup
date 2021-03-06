package dan.nr.mvvm_signup.repository

import dan.nr.mvvm_signup.network.Resource
import dan.nr.mvvm_signup.network.UserApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository
{
    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T>
    {
        return withContext(Dispatchers.IO) {
            try
            {
                Resource.Success(apiCall.invoke())
            }
            catch (t: Throwable)
            {
                when (t)
                {
                    is HttpException ->
                    {
                        Resource.Failure(false, t.code(), t.response()?.errorBody())
                    }
                    else             ->
                    {
                        Resource.Failure(true, null, null)
                    }
                }
            }
        }
    }

    suspend fun logout(api:UserApi)=safeApiCall {
        api.userLogout()
    }
}