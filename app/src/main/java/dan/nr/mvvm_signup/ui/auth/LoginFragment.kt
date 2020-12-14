package dan.nr.mvvm_signup.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import dan.nr.mvvm_signup.databinding.FragmentLoginBinding
import dan.nr.mvvm_signup.network.AuthApi
import dan.nr.mvvm_signup.network.Resource
import dan.nr.mvvm_signup.repository.AuthRepository
import dan.nr.mvvm_signup.ui.base.BaseFragment
import dan.nr.mvvm_signup.ui.home.HomeActivity
import dan.nr.mvvm_signup.utils.TAG
import dan.nr.mvvm_signup.utils.isViewEnable
import dan.nr.mvvm_signup.utils.setViewVisibility
import dan.nr.mvvm_signup.utils.startNewActivity

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>()
{
    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

        binding.progressBar.setViewVisibility(false)
        binding.btnLogin.isViewEnable(false)

        viewModel.loginResponse.observe(this.viewLifecycleOwner, Observer { response ->
            binding.progressBar.setViewVisibility(false)
            when (response)
            {
                is Resource.Success ->
                {
                    viewModel.saveAuthToken(response.value.user.access_token)
                    requireActivity().startNewActivity(HomeActivity::class.java)

                    Log.i(TAG, "LoginFragment:Request successful")
                }
                is Resource.Failure ->
                {
                    Log.i(TAG, "LoginFragment:Request failure because :" + response.errorBody)
                }
            }

        })

        binding.btnLogin.setOnClickListener {
            val email: String = binding.edtEmail.text.toString().trim()
            val password: String = binding.edtPassword.text.toString().trim()

            binding.progressBar.setViewVisibility(true)
            //todo add input validations
            viewModel.login(email = email, password = password)
        }

        binding.edtPassword.addTextChangedListener {
            doValidations()
        }
        binding.edtEmail.addTextChangedListener {
            doValidations()
        }
    }

    fun doValidations()
    {
        val email: String = binding.edtEmail.text.toString().trim()
        val password: String = binding.edtPassword.text.toString().trim()
        binding.btnLogin.isViewEnable(email.isNotEmpty() && password.isNotEmpty())
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(inflater: LayoutInflater,
                                    container: ViewGroup?
                                   ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(AuthApi::class.java),
                                                          userPreferences)


}
