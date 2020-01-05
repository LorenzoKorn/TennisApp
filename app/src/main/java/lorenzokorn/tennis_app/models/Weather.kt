package lorenzokorn.tennis_app.models

import com.google.gson.annotations.SerializedName

class WeatherData(
    @SerializedName("weather") var weather: List<Weather?>,
    @SerializedName("main") var main_: Main? = null,
    @SerializedName("wind") var wind: Wind? = null,
    @SerializedName("sys") var sys: Sys? = null,
    @SerializedName("name") var name: String? = null
) {
    override fun toString(): String {
        return "weather:${weather[0]?.id} ${weather[0]?.main_} ${weather[0]?.description} \n" +
                "main: ${main_?.temp} ${main_?.tempMin} ${main_?.tempMax} | ${main_?.pressure} ${main_?.humidity} \n" +
                "wind: ${wind?.speed} ${wind?.deg} \n" +
                "sys: ${sys?.country} ${sys?.sunrise} ${sys?.sunset}"
    }
}

class Weather(
    @SerializedName("id") var id: Long? = null,
    @SerializedName("main") var main_: String? = null,
    @SerializedName("description") var description: String? = null
)

class Main(
    @SerializedName("temp") var temp: Double? = null,
    @SerializedName("pressure") var pressure: Double? = null,
    @SerializedName("humidity") var humidity: Double? = null,
    @SerializedName("temp_min") var tempMin: Double? = null,
    @SerializedName("temp_max") var tempMax: Double? = null
)

class Wind(
    @SerializedName("speed") var speed: Double? = null,
    @SerializedName("deg") var deg: Double? = null
)

class Sys(
    @SerializedName("country") var country: String? = null,
    @SerializedName("sunrise") var sunrise: Long? = null,
    @SerializedName("sunset") var sunset: Long? = null
)