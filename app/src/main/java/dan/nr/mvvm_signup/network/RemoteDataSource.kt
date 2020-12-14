package dan.nr.mvvm_signup.network

import androidx.viewbinding.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RemoteDataSource
{
    companion object
    {
        private const val BASE_URL = "http://192.168.1.103:8090/OAuth2_Laravel/public/api/"
    }

    fun <Api> buildApi(api: Class<Api>, authToken: String? = null): Api
    {

        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder()
                                .addInterceptor { chain ->
                                    chain.proceed(chain.request().newBuilder().also {
                                        it.addHeader("Authorization", "Bearer $authToken")
                                    }.build())

                                }.also { client ->
                            if (BuildConfig.DEBUG)
                            {
                                val logging = HttpLoggingInterceptor()
                                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                                client.addInterceptor(logging)
                            }
                        }.build())
                .build()
                .create(api)
    }
}