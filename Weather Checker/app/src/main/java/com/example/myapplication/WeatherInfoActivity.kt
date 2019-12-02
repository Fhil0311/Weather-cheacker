package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.myapplication.Data.AppDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_weather_info.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WeatherInfoActivity : AppCompatActivity() {
    private var db: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_info)


        GlobalScope.launch(Dispatchers.Main) {
            db = AppDatabase.getInstance(applicationContext)
            getWeatherFromDB()
        }


    }

    fun onHistoryItemClicked(item: MenuItem) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun onMapItemClicked(item: MenuItem) {
        val intent = Intent(this, MapActivity::class.java)
        startActivity(intent)
    }

    private suspend fun getWeatherFromDB() {
        val dbDao = db!!.weatherDao()
        val request = dbDao.getWeather()


        if (weather_info.text != "") {
            Toast.makeText(
                applicationContext,
                "Your already watched this weather today :)",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            weather_info?.text = request.toString()
        }
    }
}


