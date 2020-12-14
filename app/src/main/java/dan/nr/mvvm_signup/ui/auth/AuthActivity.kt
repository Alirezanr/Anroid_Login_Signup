package dan.nr.mvvm_signup.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import dan.nr.mvvm_signup.R
import dan.nr.mvvm_signup.ui.home.HomeActivity
import dan.nr.mvvm_signup.utils.UserPreferences
import dan.nr.mvvm_signup.utils.startNewActivity

class AuthActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        val userPreferences = UserPreferences(this)
        userPreferences.authToken.asLiveData().observe(this, Observer {
            if (it != null)
            {
                startNewActivity(HomeActivity::class.java)
            } else
                setContentView(R.layout.activity_auth)
        })


    }
}
