package dan.nr.mvvm_signup.ui.viewmodel

import androidx.lifecycle.ViewModel
import dan.nr.mvvm_signup.repository.AuthRepository
import dan.nr.mvvm_signup.repository.BaseRepository

class AuthViewModel(
    private val repository: AuthRepository
):ViewModel() {
}