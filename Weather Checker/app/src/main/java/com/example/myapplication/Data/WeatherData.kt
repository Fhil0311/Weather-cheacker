package com.example.myapplication.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import javax.xml.transform.Source

@Entity(
    tableName = "weatherData"
)
//data class WeatherData(
//    @PrimaryKey(autoGenerate = true)
//    @Json(name = "userId")
//    val userId: Long,
//
//    @ColumnInfo(name = "id")
//    @Json(name = "id")
//    val id: Long,
//
//    @ColumnInfo(name = "title")
//    @Json(name = "title")
//    val title: String,
//
//    @ColumnInfo(name = "body")
//    @Json(name = "body")
//    val body: String

//)

data class WeatherData(
    @PrimaryKey(autoGenerate = true)
    var primaryKey: Int,
    var notParsedJson: String
//    var id: Long,
//    var effectiveData: String,
//    var effectiveEpochDate: Long,
//    var severity: Int,
//    var text: String,
//    var category: String,
//    var endDate: String,
//    var endEpochDate: Long,
//    var mobileLink: String,
//    var link: String,
//    var date: String,
//    var epochDate: Long,
//    var Value: Double,
//    var Unit: String,
//    var UnitType: Int,
//    var icon: Int,
//    var iconPhrase: String,
//    var hasPrecipitation: Boolean,
//    var precipitationType: String,
//    var precipitationIntensit: String,
//    var res: String
)

