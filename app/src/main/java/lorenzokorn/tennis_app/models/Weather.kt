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

/*
{
    "coord": {
        "lon": -0.13,
        "lat": 51.51
    },
    "weather": [
        {
            "id": 300,
            "main": "Drizzle",
            "description": "light intensity drizzle",
            "icon": "09d"
        }
    ],
    "base": "stations",
    "main": {
        "temp": 280.32,
        "pressure": 1012,
        "humidity": 81,
        "temp_min": 279.15,
        "temp_max": 281.15
    },
    "visibility": 10000,
    "wind": {
        "speed": 4.1,
        "deg": 80
    },
    "clouds": {
        "all": 90
    },
    "dt": 1485789600,
    "sys": {
        "type": 1,
        "id": 5091,
        "message": 0.0103,
        "country": "GB",
        "sunrise": 1485762037,
        "sunset": 1485794875
    },
    "id": 2643743,
    "name": "London",
    "cod": 200
}
* */