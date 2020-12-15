package dan.nr.mvvm_signup.utils

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import dan.nr.mvvm_signup.network.Resource
import dan.nr.mvvm_signup.ui.auth.LoginFragment

const val TAG = "LOG_TAG"

fun <A : Activity> Activity.startNewActivity(activity: Class<A>)
{
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

fun View.setViewVisibility(isVisible: Boolean)
{
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.isViewEnable(enabled: Boolean)
{
    isEnabled = enabled
    alpha = if (enabled) 1f else 0.9f
}

fun View.snackBar(message: String, action: (() -> Unit)? = null)
{
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    action?.let {
        snackbar.setAction("Retry") {
            it()
        }
    }
}

fun Fragment.handleApiError(failure: Resource.Failure,
                            retry: (() -> Unit)? = null)
{
    when
    {
        failure.isNetworkError ->
        {
            requireView().snackBar("Please check your network connection.", retry)
        }
        failure.errorCode == 401 ->
        {
            if (this is LoginFragment)
            {
                requireView().snackBar("You have entered wrong email or password")
            } else
            {
                //todo perform logout
            }
        }
        else ->
        {
            requireView().snackBar(failure.errorBody.toString())
        }
    }
}