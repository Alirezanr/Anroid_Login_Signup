package dan.nr.mvvm_signup.ui.fragment.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dan.nr.mvvm_signup.repository.AuthRepository
import dan.nr.mvvm_signup.repository.BaseRepository
import dan.nr.mvvm_signup.ui.viewmodel.AuthViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val repository: BaseRepository) :
    ViewModelProvider.NewInstanceFactory()
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
    {
        return when
        {
            modelClass.isAssignableFrom(AuthViewModel::class.java) ->
            {
                AuthViewModel(repository = repository as AuthRepository) as T
            }
            else                                                   ->
            {
                throw IllegalArgumentException("ViewModel class not found")
            }
        }
    }
}