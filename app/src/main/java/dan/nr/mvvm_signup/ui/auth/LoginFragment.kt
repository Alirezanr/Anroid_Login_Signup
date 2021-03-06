package dan.nr.mvvm_signup.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dan.nr.mvvm_signup.R
import dan.nr.mvvm_signup.databinding.FragmentLoginBinding
import dan.nr.mvvm_signup.network.AuthApi
import dan.nr.mvvm_signup.network.Resource
import dan.nr.mvvm_signup.repository.AuthRepository
import dan.nr.mvvm_signup.ui.base.BaseFragment
import dan.nr.mvvm_signup.ui.home.HomeActivity
import dan.nr.mvvm_signup.utils.*
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>()
{
    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

        binding.progressBar.setViewVisibility(false)
        binding.btnLogin.isViewEnable(false)

        viewModel.loginResponse.observe(this.viewLifecycleOwner,
                                        { response ->
                                            binding.progressBar.setViewVisibility(response is Resource.Loading)

                                            when (response)
                                            {
                                                is Resource.Success ->
                                                {
                                                    lifecycleScope.launch {
                                                        viewModel.saveAuthToken(response.value.user.access_token!!)
                                                        requireActivity().startNewActivity(HomeActivity::class.java)
                                                    }
                                                }
                                                is Resource.Failure ->
                                                {
                                                    handleApiError(response) { login() }
                                                }
                                            }
                                        })

        binding.btnLogin.setOnClickListener {
            login()
        }

        binding.edtPassword.addTextChangedListener {
            doValidations()
        }
        binding.edtEmail.addTextChangedListener {
            doValidations()
        }

        binding.txtSignup.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegistrationFragment())
        }
    }

    private fun login()
    {
        val email: String = binding.edtEmail.text.toString().trim()
        val password: String = binding.edtPassword.text.toString().trim()

        viewModel.login(email = email,
                        password = password)
    }

    private fun doValidations()
    {
        val email: String = binding.edtEmail.text.toString().trim()
        val password: String = binding.edtPassword.text.toString().trim()
        binding.btnLogin.isViewEnable(email.isNotEmpty() && password.isNotEmpty())
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) =
            FragmentLoginBinding.inflate(inflater,
                                         container,
                                         false)

    override fun getFragmentRepository() =
            AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)
}
