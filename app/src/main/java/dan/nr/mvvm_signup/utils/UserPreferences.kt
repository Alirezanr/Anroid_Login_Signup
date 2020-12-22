package dan.nr.mvvm_signup.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.createDataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.clear
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences(context: Context)
{
    private val applicationContext = context.applicationContext
    private val dataStore: DataStore<Preferences>

    init
    {
        dataStore = applicationContext.createDataStore(name = "my_data_store")
    }

    val authToken: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[AUTH_KEY]
        }

    suspend fun saveAuthToken(authToken: String)
    {
        dataStore.edit { preferences ->
            preferences[AUTH_KEY] = authToken
        }
    }

    suspend fun clear()
    {
        dataStore.edit { preference ->
            preference.clear()
        }
    }

    companion object
    {
        private val AUTH_KEY = preferencesKey<String>("auth_key")
    }
}