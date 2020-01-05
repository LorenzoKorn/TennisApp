package lorenzokorn.tennis_app.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import lorenzokorn.tennis_app.database.WeatherRepository
import lorenzokorn.tennis_app.models.WeatherData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel(application: Application) : AndroidViewModel(application) {
    private val moviesRepository: WeatherRepository = WeatherRepository()
    val weather = MutableLiveData<WeatherData>()
    val error = MutableLiveData<String>()

    fun getWeatherFromId(id: Long) {
        moviesRepository.getWeatherFromId(id).enqueue(object : Callback<WeatherData?> {
            override fun onResponse(call: Call<WeatherData?>, response: Response<WeatherData?>) {
                if (response.isSuccessful) {
                    weather.value = response.body()!!
                } else {
                    error.value = "An error has occurred: ${response.errorBody().toString()}"
                }
            }

            override fun onFailure(call: Call<WeatherData?>, t: Throwable) {
                error.value = t.message
            }
        })
    }
}