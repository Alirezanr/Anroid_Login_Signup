package dan.nr.mvvm_signup.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dan.nr.mvvm_signup.databinding.FragmentSignupBinding
import dan.nr.mvvm_signup.network.AuthApi
import dan.nr.mvvm_signup.network.Resource
import dan.nr.mvvm_signup.repository.AuthRepository
import dan.nr.mvvm_signup.ui.base.BaseFragment
import dan.nr.mvvm_signup.ui.home.HomeActivity
import dan.nr.mvvm_signup.utils.*
import kotlinx.coroutines.launch

class RegisterFragment : BaseFragment<AuthViewModel, FragmentSignupBinding, AuthRepository>()
{
    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

        binding.progressBar.setViewVisibility(false)
        binding.btnRegister.isViewEnable(false)

        viewModel.signupResponse.observe(this,
                                         { response ->
                                             binding.progressBar.setViewVisibility(response is Resource.Loading)
                                             when (response)
                                             {
                                                 is Resource.Success ->
                                                 {
                                                     lifecycleScope.launch {
                                                         viewModel.saveAuthToken(response.value.user!!.access_token!!)
                                                         requireActivity().startNewActivity(HomeActivity::class.java)
                                                     }
                                                 }
                                                 is Resource.Failure ->
                                                 {
                                                     handleApiError(response) { signup() }
                                                 }
                                             }
                                         })
        binding.txtLogin.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionRegistrationFragmentToLoginFragment())
        }
        binding.edtEmail.addTextChangedListener {
            doValidations()
        }
        binding.edtName.addTextChangedListener {
            doValidations()
        }
        binding.edtPassword.addTextChangedListener {
            doValidations()
        }

        binding.edtPasswordRepeat.addTextChangedListener {
            doValidations()
        }

        binding.btnRegister.setOnClickListener {
            signup()
        }
    }

    private fun signup()
    {
        val name: String = binding.edtName.text.toString().trim()
        val email: String = binding.edtEmail.text.toString().trim()
        val password: String = binding.edtPassword.text.toString().trim()
        val passwordRepeat: String = binding.edtPasswordRepeat.text.toString().trim()
        viewModel.signup(email,
                         name,
                         password,
                         passwordRepeat)
    }

    private fun doValidations()
    {
        val email: String = binding.edtEmail.text.toString().trim()
        val name: String = binding.edtName.text.toString().trim()
        val password: String = binding.edtPassword.text.toString().trim()
        val password_confirmation: String = binding.edtPasswordRepeat.text.toString().trim()
        binding.btnRegister.isViewEnable(name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && password_confirmation.isNotEmpty())
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) =
            FragmentSignupBinding.inflate(inflater,
                                          container,
                                          false)


    override fun getFragmentRepository() =
            AuthRepository(remoteDataSource.buildApi(AuthApi::class.java),
                           userPreferences)

}