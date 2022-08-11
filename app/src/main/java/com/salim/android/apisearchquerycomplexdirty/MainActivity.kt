package com.salim.android.apisearchquerycomplexdirty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.salim.android.apisearchquerycomplexdirty.adapter.DefinationAdapter
import com.salim.android.apisearchquerycomplexdirty.data.DefinitionsItem
import com.salim.android.apisearchquerycomplexdirty.data.DictionaryResponseItem
import com.salim.android.apisearchquerycomplexdirty.databinding.ActivityMainBinding
import com.salim.android.apisearchquerycomplexdirty.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val definitionAdapter = DefinationAdapter()

    var wordDictionary = ArrayList<DictionaryResponseItem>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        searchApi()
    }

    private fun setView() {

        binding.tvName.text = wordDictionary.get(0).word

        binding.rvDevination.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            definitionAdapter.setDefinition(wordDictionary.get(0).meanings?.get(0)?.definitions)
            adapter = definitionAdapter
        }

    }

    private fun searchApi() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                getDataApiWord(p0.toString())
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })
    }

    private fun getDataApiWord(word: String) {
        ApiConfig.getApiService().getWordDetail(word)
            .enqueue(object : Callback<ArrayList<DictionaryResponseItem>> {
                override fun onResponse(
                    call: Call<ArrayList<DictionaryResponseItem>>,
                    response: Response<ArrayList<DictionaryResponseItem>>
                ) {
                    wordDictionary = response.body()!!
                    setView()

                }
                override fun onFailure(
                    call: Call<ArrayList<DictionaryResponseItem>>,
                    t: Throwable
                ) {
                    Log.e("TAG", "onFailure: $t")
                }

            })
    }

}
