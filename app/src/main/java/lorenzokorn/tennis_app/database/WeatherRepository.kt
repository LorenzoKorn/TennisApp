package lorenzokorn.tennis_app.database

import lorenzokorn.tennis_app.api.WeatherApi
import lorenzokorn.tennis_app.api.WeatherService

class WeatherRepository {
    private val moviesApi: WeatherService = WeatherApi.createApi()

    fun getWeatherFromId(id: Long) = moviesApi.getWeatherFromId(id)
}