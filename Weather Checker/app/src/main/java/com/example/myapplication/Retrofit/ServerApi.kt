package com.example.myapplication.Retrofit

import androidx.room.FtsOptions.Order
import com.example.myapplication.Data.WeatherData
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Path




interface ServerApi {


  //  @GET("forecasts/v1/daily/1day/2580?apikey=jCQLk1CgIamq5WUF8lpzRtUeuPnSYBGY")
   // @GET("posts")

    @GET("forecasts/v1/daily/1day/2580?apikey=aNYRd9f5ttfxCM2T0rQ92nItg1HtKh6K")
    fun getWeather(): Call <ResponseBody>

}