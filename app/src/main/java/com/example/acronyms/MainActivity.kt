package com.example.acronyms

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.acronyms.adapter.AcronymAdapter
import com.example.acronyms.data.model.AcronymsViewModel
import com.example.acronyms.databinding.ActivityMainBinding
import org.json.JSONObject
import org.json.JSONTokener

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
            rvAcronym.adapter = adapter
            rvAcronym.setHasFixedSize(true)

            etQuery.doAfterTextChanged {
                searchAcronyms()
            }
        }
        viewModel.getSearchAcronyms().observe(this, {
            if (it != null && !it.isNullOrEmpty()) {
                adapter.setList(it)
                showLoading(false)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        binding.apply {
            btnSearch.setOnClickListener {
                searchAcronyms()
            }
        }
    }

    private fun searchAcronyms() {
        binding.apply {
            val sf = etQuery.text
            viewModel.setSearchAcronyms(
                sf.toString()
            )
            if (sf != null) {
                return showLoading(true)
            }
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.VISIBLE
        }
    }
}
