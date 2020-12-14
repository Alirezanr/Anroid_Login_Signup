package dan.nr.mvvm_signup.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import dan.nr.mvvm_signup.R
import dan.nr.mvvm_signup.ui.auth.AuthActivity
import dan.nr.mvvm_signup.ui.home.HomeActivity
import dan.nr.mvvm_signup.utils.UserPreferences
import dan.nr.mvvm_signup.utils.startNewActivity

class StartActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_start)

        val userPreferences = UserPreferences(this)
        userPreferences.authToken.asLiveData().observe(this, Observer {
            val activity = if (it != null) HomeActivity::class.java else AuthActivity::class.java
            startNewActivity(activity)
        })
    }
}