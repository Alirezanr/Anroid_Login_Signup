package dan.nr.mvvm_signup.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dan.nr.mvvm_signup.network.UserApi
import dan.nr.mvvm_signup.repository.BaseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(private val repository: BaseRepository) : ViewModel()
{
    suspend fun logout(api: UserApi) = withContext(Dispatchers.IO) {
        repository.logout(api)
    }
}