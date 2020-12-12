package dan.nr.mvvm_signup.ui.fragment.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dan.nr.mvvm_signup.R
import dan.nr.mvvm_signup.databinding.FragmentLoginBinding
import dan.nr.mvvm_signup.network.AuthApi
import dan.nr.mvvm_signup.repository.AuthRepository
import dan.nr.mvvm_signup.ui.fragment.base.BaseFragment
import dan.nr.mvvm_signup.ui.viewmodel.AuthViewModel

class LognFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>()
{
    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        viewModel
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(inflater: LayoutInflater,
                                    container: ViewGroup?
                                   ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(AuthApi::class.java))


}