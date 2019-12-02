package com.example.myapplication.Data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weatherData: WeatherData)

    @Query("Select * from weatherData LIMIT 1")
    suspend fun getWeather(): WeatherData

    @Query("Select * from weatherData ")
    suspend fun getAllWeatherFromDB(): List<WeatherData>


    @Query("DELETE FROM weatherData")
    suspend fun deleteAllWeather()


//    @Query("SELECT * FROM weatherData  WHERE userId = :userId")
//    suspend  fun getWeatherById(userId: Long): WeatherData
//
//    @Query("SELECT * FROM weatherData  ORDER BY RANDOM() LIMIT 1")
//    suspend  fun getRandomWheather(): WeatherData
//
//    @Query("SELECT userId FROM weatherData  WHERE userId = :userId LIMIT 1")
//    suspend  fun getUserId(userId: Long): Long
//
//    @Query("SELECT id FROM weatherData ORDER BY RANDOM() LIMIT 1")
//    suspend  fun getId(): Long
//
//    @Query("SELECT title FROM weatherData ORDER BY RANDOM() LIMIT 1")
//    suspend  fun getTitle(): String
//
//    @Query("SELECT body FROM weatherData  ORDER BY RANDOM() LIMIT 1")
//    suspend  fun getBody(): String


}