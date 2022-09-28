package com.example.acronyms

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.acronyms.adapter.AcronymAdapter
import com.example.acronyms.adapter.LfXJsonAdapter
import com.example.acronyms.data.model.AcronymTestMoshiItem
import com.example.acronyms.data.model.AcronymsViewModel
import com.example.acronyms.data.model.LfX
import com.example.acronyms.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: AcronymsViewModel
    private lateinit var adapter: AcronymAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = AcronymAdapter()
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[AcronymsViewModel::class.java]

        binding.apply {
            rvAcronym.layoutManager = LinearLayoutManager(this@MainActivity)
            rvAcronym.setHasFixedSize(true)
            rvAcronym.adapter = adapter

            btnSearch.setOnClickListener {
                searchAcronyms()
            }

            etQuery.doAfterTextChanged {
                searchAcronyms()
            }
        }
        viewModel.getSearchAcronyms().observe(this, {
            if (it != null) {
                adapter.setList(it.copy())
                showLoading(false)
            }
        })
        lifecycleScope.launch(Dispatchers.IO) {
            Log.d("", "onCreate: Inside launch IO")
        }
        thread {
            Log.d("", "onCreate: Inside thread!")
        }
    }

    private fun searchAcronyms() {
        binding.apply {
            val sf = etQuery.text.toString()
            if (sf.isNotEmpty()) return
            showLoading(true)
            viewModel.setSearchAcronyms("HMM")
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}
