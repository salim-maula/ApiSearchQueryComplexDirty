package com.salim.android.apisearchquerycomplexdirty.network

import com.salim.android.apisearchquerycomplexdirty.data.DefinitionsItem
import com.salim.android.apisearchquerycomplexdirty.data.DictionaryResponseItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("en/{word}")
    fun getWordDetail(@Path("word") word: String): Call<ArrayList<DictionaryResponseItem>>
}