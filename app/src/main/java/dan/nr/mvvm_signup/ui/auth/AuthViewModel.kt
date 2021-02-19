package dan.nr.mvvm_signup.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dan.nr.mvvm_signup.network.Resource
import dan.nr.mvvm_signup.model.responses.auth.LoginResponse
import dan.nr.mvvm_signup.model.responses.auth.RegistrationResponse
import dan.nr.mvvm_signup.repository.AuthRepository
import dan.nr.mvvm_signup.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AuthRepository) : BaseViewModel(repository)
{
    private val _loginResponse = MutableLiveData<Resource<LoginResponse>>()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    private val _signupResponse = MutableLiveData<Resource<RegistrationResponse>>()
    val signupResponse: LiveData<Resource<RegistrationResponse>>
        get() = _signupResponse


    fun login(email: String, password: String) = viewModelScope.launch {
        _loginResponse.value = Resource.Loading
        _loginResponse.value = repository.login(email = email,
                                                password = password)
    }

    fun signup(email: String, name: String, password: String, password_confirmation: String) =
            viewModelScope.launch {
                _signupResponse.value = Resource.Loading
                _signupResponse.value = repository.signup(email = email,
                                                          name = name,
                                                          password = password,
                                                          password_confirmation = password_confirmation)
            }

    suspend fun saveAuthToken(token: String)
    {
        repository.saveAuthToken(token)
    }
}