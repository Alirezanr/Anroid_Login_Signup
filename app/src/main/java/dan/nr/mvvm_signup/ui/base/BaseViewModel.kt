package dan.nr.mvvm_signup.ui.base

import androidx.lifecycle.ViewModel
import dan.nr.mvvm_signup.repository.BaseRepository

abstract class BaseViewModel(private val repository: BaseRepository): ViewModel()
{
}