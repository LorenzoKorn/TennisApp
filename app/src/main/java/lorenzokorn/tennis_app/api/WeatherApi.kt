package lorenzokorn.tennis_app.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherApi {
    companion object {
        // baseUrl has to end with '/'
        private const val baseUrl = "https://api.openweathermap.org/data/2.5/"

        /**
         * @return [WeatherService] The service class off the retrofit client
         */
        fun createApi(): WeatherService {
            //
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()

            // Create retrofit instance
            val weatherApi = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            // Return the retrofit WeatherService
            return weatherApi.create(WeatherService::class.java)
        }
    }
}