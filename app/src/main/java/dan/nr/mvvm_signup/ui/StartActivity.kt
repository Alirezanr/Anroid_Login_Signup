package dan.nr.mvvm_signup.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import dan.nr.mvvm_signup.R
import dan.nr.mvvm_signup.ui.auth.AuthActivity
import dan.nr.mvvm_signup.ui.home.HomeActivity
import dan.nr.mvvm_signup.utils.UserPreferences
import dan.nr.mvvm_signup.utils.startNewActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect

class StartActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val userPreferences = UserPreferences(this)
        lifecycleScope.launchWhenStarted {
            userPreferences.authToken.collect {
                delay(1000)
                val activity = if (it != null) HomeActivity::class.java else AuthActivity::class.java
                startNewActivity(activity)
            }
        }
    }
}