package dan.nr.mvvm_signup.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dan.nr.mvvm_signup.network.Resource
import dan.nr.mvvm_signup.model.responses.LoginResponse
import dan.nr.mvvm_signup.repository.AuthRepository
import kotlinx.coroutines.launch

private const val TAG = "LOG_TAG"
class AuthViewModel(private val repository: AuthRepository) : ViewModel()
{
    private val _loginResponse = MutableLiveData<Resource<LoginResponse>>()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() =_loginResponse

    fun login(email: String, password: String) = viewModelScope.launch {
        _loginResponse.value = repository.login(email = email, password = password)
       // Log.i(TAG, _loginResponse.value.toString())
    }
}