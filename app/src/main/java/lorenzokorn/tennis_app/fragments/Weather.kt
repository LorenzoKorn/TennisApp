package lorenzokorn.tennis_app.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_weather.*

import lorenzokorn.tennis_app.R
import lorenzokorn.tennis_app.models.Cities
import lorenzokorn.tennis_app.models.WeatherData
import lorenzokorn.tennis_app.viewmodels.WeatherViewModel
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class Weather : Fragment() {

    lateinit var city: Cities
    private lateinit var viewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initSpinner()
    }

    private fun initViewModel() {
        viewModel.weather.observe(this, Observer {
            Log.w("weather", "$it")
            displayData(it)
        })
        viewModel.error.observe(this, Observer {
            Log.w("error", it)
        })
        getWeather()
    }

    private fun initSpinner() {
        val cities = arrayListOf<Cities>()
        enumValues<Cities>().forEach { cities.add(it) }
        Log.w("city", "$cities")

        val adapter = ArrayAdapter<Cities>(context!!, android.R.layout.simple_spinner_item, cities)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        city_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                city = parent.selectedItem as Cities
                getWeather()
            }
        }
    }

    private fun getWeather() {
//        viewModel.getWeatherFromId(city.id)
        viewModel.getWeatherFromId(Cities.AMSTERDAM.id)
    }

    private fun displayData(data: WeatherData) {
        id_weather.text     = getString(R.string.location_id, data.weather[0]?.id)
        main.text           = getString(R.string.main, data.weather[0]?.main_)
        description.text    = getString(R.string.description, data.weather[0]?.description)
        temp.text           = getString(R.string.temp, data.main_?.temp)
        temp_min.text       = getString(R.string.temp_min, data.main_?.tempMin)
        temp_max.text       = getString(R.string.temp_max, data.main_?.tempMax)
        pressure.text       = getString(R.string.pressure, data.main_?.pressure)
        humidity.text       = getString(R.string.humidity, data.main_?.humidity, "%")
        speed.text          = getString(R.string.speed, data.wind?.speed)
        deg.text            = getString(R.string.degrees, data.wind?.deg)
        country.text        = getString(R.string.country, data.sys?.country)
        sunrise.text        = getString(R.string.sunrise, Date(data.sys?.sunrise!!).toString())
        sunset.text         = getString(R.string.sunset, Date(data.sys?.sunset!!).toString())
    }
}
