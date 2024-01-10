package com.developersiam.dictionary

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.developersiam.dictionary.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

//TODO: Add Logo & Spalsh Screen
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var adapter: MeaningAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.searchButton.setOnClickListener {
            val word = binding.searchBar.text.toString()
            getMeaning(word)
        }

        adapter = MeaningAdapter(emptyList())
        binding.meaningRV.layoutManager = LinearLayoutManager(this)
        binding.meaningRV.adapter =adapter
    }

    @SuppressLint("SuspiciousIndentation")
    private fun getMeaning(word : String){
        setInProgress(true)
        GlobalScope.launch {
          val response = RetrofitInstance.dictionaryApi.getMeaning(word)
            runOnUiThread{
                setInProgress(false)
                response.body()?.first().let {
                    setUi(it!!)
                }
            }
           }
    }

    private fun setUi(response : WordResult){
        binding.wordBox.text = response.word
        binding.phoneticsBox.text = response.phonetic
        adapter.updateNewData(response.meanings)
    }

    private fun setInProgress(inProgress: Boolean){
        if (inProgress) {
            binding.searchButton.visibility = View.INVISIBLE
            binding.progressBar.visibility  = View.VISIBLE
        }
        else{
            binding.searchButton.visibility = View.VISIBLE
            binding.progressBar.visibility  = View.INVISIBLE
        }

        }
    }