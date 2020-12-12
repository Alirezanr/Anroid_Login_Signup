package dan.nr.mvvm_signup.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dan.nr.mvvm_signup.network.Resource
import dan.nr.mvvm_signup.network.responses.LoginResponse
import dan.nr.mvvm_signup.repository.AuthRepository
import dan.nr.mvvm_signup.repository.BaseRepository
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AuthRepository) : ViewModel()
{
    private val _loginResponse = MutableLiveData<Resource<LoginResponse>>()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() =_loginResponse

    fun login(email: String, password: String) = viewModelScope.launch {
        _loginResponse.value = repository.login(email = email, password = password)
    }
}