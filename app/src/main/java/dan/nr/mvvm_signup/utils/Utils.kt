package dan.nr.mvvm_signup.utils

import android.app.Activity
import android.content.Intent
import android.view.View

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
}

