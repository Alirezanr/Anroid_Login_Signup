package dan.nr.mvvm_signup.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dan.nr.mvvm_signup.model.responses.auth.LoginResponse
import dan.nr.mvvm_signup.network.Resource
import dan.nr.mvvm_signup.repository.UserRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel(private val repository: UserRepository) : ViewModel()
{
    private val _user = MutableLiveData<Resource<LoginResponse>>()
    val user: LiveData<Resource<LoginResponse>>
        get() = _user

    fun getUser() = viewModelScope.launch {
        _user.value = Resource.Loading
        _user.value = repository.getUser()
    }
}
