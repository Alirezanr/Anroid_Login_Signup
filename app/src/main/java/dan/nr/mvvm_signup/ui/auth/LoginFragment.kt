package dan.nr.mvvm_signup.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import dan.nr.mvvm_signup.databinding.FragmentLoginBinding
import dan.nr.mvvm_signup.network.AuthApi
import dan.nr.mvvm_signup.network.Resource
import dan.nr.mvvm_signup.repository.AuthRepository
import dan.nr.mvvm_signup.ui.base.BaseFragment
import dan.nr.mvvm_signup.utils.TAG

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>()
{
    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        viewModel.loginResponse.observe(this.viewLifecycleOwner, Observer { response ->
            when (response)
            {
                is Resource.Success ->
                {
                    Toast.makeText(requireContext(), " Success :$response", Toast.LENGTH_SHORT).show()
                    Log.i(TAG, "LoginFragment:Request successful")
                }
                is Resource.Failure ->
                {
                    Toast.makeText(requireContext()," Failure :"+response.errorBody.toString(), Toast.LENGTH_SHORT).show()
                    Log.i(TAG, "BaseRepository:Requset successfull")
                }
            }

        })

        binding.btnLogin.setOnClickListener {
            val email: String = binding.edtEmail.text.toString().trim()
            val password: String = binding.edtPassword.text.toString().trim()
            //todo add input validations
            viewModel.login(email = email, password = password)
        }
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(inflater: LayoutInflater,
                                    container: ViewGroup?
                                   ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(AuthApi::class.java))
}
