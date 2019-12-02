package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.myapplication.Data.AppDatabase
import com.example.myapplication.Data.WeatherDao
import com.example.myapplication.Data.WeatherData
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_weather_info.*
import kotlinx.android.synthetic.main.list_item_history.*
import kotlinx.android.synthetic.main.list_item_history.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class MainActivity : AppCompatActivity() {
    private var db: AppDatabase? = null
    //TODO replace activity with fragments!!

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rvHistory)
        recyclerView.layoutManager = LinearLayoutManager(this)

        GlobalScope.launch(Dispatchers.IO) {
            db = AppDatabase.getInstance(applicationContext)
            recyclerView.adapter = InProgressAdapter(getAllWeatherFromDB(), this@MainActivity)

        }
    }

    class InProgressAdapter(val items: List<WeatherData>, val context: Context) :
        RecyclerView.Adapter<ViewHolder>() {

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.list_item_history,
                    parent,
                    false
                )
            )
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.targetHistory?.text = items[position].notParsedJson
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val targetHistory = view.target_history
    }


    private suspend fun insertWeatherDataInDb(weather: WeatherData) {
        val dbDao = db!!.weatherDao()
        dbDao.insertWeather(weather)

    }


    private suspend fun getAllWeatherFromDB(): List<WeatherData> {
        val dbDao = db!!.weatherDao()
        val request = dbDao.getAllWeatherFromDB()
        return request
    }


    fun onHistoryItemClicked(item: MenuItem) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun onMapItemClicked(item: MenuItem) {
        val intent = Intent(this, MapActivity::class.java)
        startActivity(intent)
    }


    fun onCardClickListener(view: View) {
        val intent = Intent(applicationContext, WeatherInfoActivity::class.java)
        startActivity(intent)
    }


}

