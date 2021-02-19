package dan.nr.mvvm_signup.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import dan.nr.mvvm_signup.databinding.FragmentHomeBinding
import dan.nr.mvvm_signup.model.responses.auth.LoginResponse
import dan.nr.mvvm_signup.model.responses.auth.User
import dan.nr.mvvm_signup.network.Resource
import dan.nr.mvvm_signup.network.UserApi
import dan.nr.mvvm_signup.repository.UserRepository
import dan.nr.mvvm_signup.ui.base.BaseFragment
import dan.nr.mvvm_signup.ui.home.HomeViewModel
import dan.nr.mvvm_signup.utils.handleApiError
import dan.nr.mvvm_signup.utils.isViewEnable
import dan.nr.mvvm_signup.utils.setViewVisibility
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding, UserRepository>()
{
    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        //  binding.progressbar.isViewEnable(false)
        binding.buttonLogout.setOnClickListener {
            logout()
        }
        viewModel.getUser()

        viewModel.user.observe(viewLifecycleOwner, Observer {
            binding.progressbar.setViewVisibility(it is Resource.Loading)
            when (it)
            {
                is Resource.Success ->
                {
                    updateUi(it.value.user)
                }
                is Resource.Failure->
                {
                    if(it.isNetworkError)
                    {
                        handleApiError(it)
                    }
                }
                is Resource.Loading ->
                {
                    binding.progressbar.isViewEnable(true)
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun updateUi(user: User)
    {
        with(binding) {
            textViewId.text= user.id.toString()
            textViewEmail.text=user.email
            textViewName.text=user.name
        }
    }


    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(inflater: LayoutInflater,
                                    container: ViewGroup?
                                   ) = FragmentHomeBinding.inflate(inflater,
                                                                   container,
                                                                   false)


    override fun getFragmentRepository(): UserRepository
    {
        val token: String? = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(UserApi::class.java, token)

        return UserRepository(api)
    }


}