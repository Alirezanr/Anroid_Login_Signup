package dan.nr.mvvm_signup.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dan.nr.mvvm_signup.repository.AuthRepository
import dan.nr.mvvm_signup.repository.BaseRepository
import dan.nr.mvvm_signup.repository.UserRepository
import dan.nr.mvvm_signup.ui.auth.AuthViewModel
import dan.nr.mvvm_signup.ui.home.HomeViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val repository: BaseRepository) : ViewModelProvider.NewInstanceFactory()
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
    {
        return when
        {
            modelClass.isAssignableFrom(AuthViewModel::class.java) ->
            {
                AuthViewModel(repository = repository as AuthRepository) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) ->
            {
                HomeViewModel(repository = repository as UserRepository) as T
            }
            else                                                   ->
            {
                throw IllegalArgumentException("ViewModel class not found")
            }
        }
    }
}