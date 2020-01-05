package lorenzokorn.tennis_app.api

import lorenzokorn.tennis_app.BuildConfig
import lorenzokorn.tennis_app.models.WeatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    // get weather from
    @GET("weather?appid=${BuildConfig.WEATER_API_KEY}")
    fun getWeatherFromId(@Query("id") id: Long): Call<WeatherData>
}