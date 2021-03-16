package com.gmail.shtukarrv.dogbreedsapp.data.local

import androidx.room.TypeConverter
import com.gmail.shtukarrv.dogbreedsapp.domain.entity.DogSubBreed
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.ParameterizedType

class DataConverter {
    private val moshi = Moshi.Builder().build()
    private val listMyData : ParameterizedType = Types.newParameterizedType(List::class.java, DogSubBreed::class.java)
    private val jsonAdapter: JsonAdapter<List<DogSubBreed>> = moshi.adapter(listMyData)

    @TypeConverter
    fun listMyModelToJsonStr(listMyModel: List<DogSubBreed>?): String? {
        return jsonAdapter.toJson(listMyModel)
    }

    @TypeConverter
    fun jsonStrToListMyModel(jsonStr: String?): List<DogSubBreed>? {
        return jsonStr?.let { jsonAdapter.fromJson(jsonStr) }
    }
}