package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Data.AppDatabase
import com.example.myapplication.Data.WeatherData
import com.example.myapplication.Retrofit.RetrofitWeatherData
//import com.example.myapplication.Retrofit.Headline
//import com.example.myapplication.Retrofit.RetrofitWeatherData
import com.example.myapplication.Retrofit.ServerApi
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_map.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap


    private var db: AppDatabase? = null
    private lateinit var map: GoogleMap
    private lateinit var marker: Marker
    override fun onCreate(savedInstanceState: Bundle?) {
        var btnNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

    }

    fun onHistoryItemClicked(item: MenuItem) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun onMapItemClicked(item: MenuItem) {
        val intent = Intent(this, MapActivity::class.java)
        startActivity(intent)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap!!




        //
        fun setRandomMarkerPosition() {
            var a = 15
            while (a != 0) {
                mMap.addMarker(
                    MarkerOptions().position(generateLatLng().first).title(
                        generateLatLng().second
                    )
                )
                a--
            }
        }
        setRandomMarkerPosition()


        Upgrade.setOnClickListener {
            googleMap.clear()
            setRandomMarkerPosition()
            Toast.makeText(applicationContext, "Upgraded!", Toast.LENGTH_SHORT).show()
        }


        val retrofit = Retrofit.Builder()
            .baseUrl("http://dataservice.accuweather.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val jsonPlaceHolder = retrofit.create(ServerApi::class.java)

        val call = jsonPlaceHolder.getWeather()

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                val body = response?.body()?.string()
//                val gson = GsonBuilder().create()
//                val content = gson.fromJson(body, RetrofitWeatherData::class.java)


                suspend fun insertWeatherDataInDb() {
                    var db = AppDatabase.getInstance(applicationContext)
                    val dbDao = db!!.weatherDao()
                    dbDao.insertWeather(WeatherData(0, body.toString()))
                }

                mMap.setOnMarkerClickListener {
                    GlobalScope.launch(Dispatchers.Main) { insertWeatherDataInDb() }
                    val intent = Intent(applicationContext, WeatherInfoActivity::class.java)
                    startActivity(intent)
                    true
                }



                Log.d("TAG", response.toString())
                Log.d("TAG", call.toString())
                Log.d("TAG", body.toString())
                Log.d("TAG", response?.body()?.source().toString())
//                Toast.makeText(applicationContext,response.body()!!.toString(), Toast.LENGTH_LONG).show()


//                var list:MutableList<WeatherData> = ArrayList<WeatherData>()
//                response!!.body()!!.forEach{inner -> (list.add(WeatherData(inner.userId,inner.id,inner.title,inner.body)))}.toString()
//                GlobalScope.launch(Dispatchers.Main) {
//                    for(Wheather in list) {
//                        insertWeatherDataInDb(Wheather)
//                    }
//                }


                if (!response!!.isSuccessful) {
                    Log.d("TAG", "Something wrong!")
                }
            }

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                Log.d("TAG", "onFailrueStart")
            }
        })


    }


    private fun generateLatLng(): Pair<LatLng, String> {
        var latitude = (-90..90).random().toDouble()
        var longitude = (0..180).random().toDouble()
        var latLng = LatLng(latitude, longitude)
        return Pair(latLng, latLng.toString())
    }


}
