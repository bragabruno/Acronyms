package com.example.acronyms.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.acronyms.data.model.AbbreviationItem
import com.example.acronyms.databinding.ActivityMainBinding
import com.example.acronyms.utils.ApiState
import com.example.acronyms.viewmodel.AcronymsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val acromineAdapter = AcronymAdapter(vars = ::showVars)
    private val viewModel: AcronymsViewModel by viewModels()
    private val varsDialog by lazy {
        VarsDialogView(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            rvAbiList.adapter = acromineAdapter
            etInput.doAfterTextChanged {
                searchAcronyms()
            }
            btnSearch.setOnClickListener {
                searchAcronyms()
            }
        }
        setViewModelObserver()
    }

    fun ActivityMainBinding.searchAcronyms() {
        if (etInput.text.isBlank()) {
            Toast.makeText(
                this@MainActivity,
                "Please enter an abbreviation",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            viewModel.fetchAbbreviation(etInput.text.toString().trim())
        }
    }

    private fun setViewModelObserver() {
        viewModel.stateLiveData.observe(this) { state: ApiState ->
            when (state) {
                is ApiState.Loading -> {
                    binding.apply {
                        pbLoading.visibility = View.VISIBLE
                        tvError.visibility = View.GONE
                        rvAbiList.visibility = View.GONE
                    }
                }
                is ApiState.Error -> {
                    binding.apply {
                        pbLoading.visibility = View.GONE
                        rvAbiList.visibility = View.GONE

                        tvError.apply {
                            text = state.exception.message
                            visibility = View.VISIBLE
                        }
                    }
                }
                is ApiState.Success -> {
                    binding.apply {
                        pbLoading.visibility = View.GONE
                        tvError.visibility = View.GONE
                        rvAbiList.apply {
                            acromineAdapter.setNewList(state.response.lfs)
                            visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    private fun showVars(vars: List<AbbreviationItem>) {
        varsDialog.buildVarsDialogView(vars)
    }

    override fun onPause() {
        super.onPause()
        varsDialog.dismiss()
    }
}
