package dan.nr.mvvm_signup.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dan.nr.mvvm_signup.R
import dan.nr.mvvm_signup.ui.auth.AuthActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        finish()
        startActivity(Intent(this, AuthActivity::class.java))
    }
}