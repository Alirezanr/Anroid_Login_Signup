package dan.nr.mvvm_signup.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import dan.nr.mvvm_signup.R
import dan.nr.mvvm_signup.ui.auth.AuthActivity
import dan.nr.mvvm_signup.utils.UserPreferences
import dan.nr.mvvm_signup.utils.startNewActivity

class HomeActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}