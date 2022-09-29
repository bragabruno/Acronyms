package com.example.acronyms.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.acronyms.data.model.AcronymTestMoshiItem
import com.example.acronyms.data.model.LfX
import com.example.acronyms.databinding.ItemAcronymBinding
import com.squareup.moshi.Moshi

class AcronymAdapter : RecyclerView.Adapter<AcronymAdapter.AcronymViewHolder>() {

    private val listAcronymsData = mutableListOf<LfX>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setList(acronyms: List<LfX>) {
        listAcronymsData.clear()
        listAcronymsData.addAll(acronyms)
        notifyDataSetChanged()
    }

    inner class AcronymViewHolder(private val binding: ItemAcronymBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(acronym: LfX) {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(acronym)
            }
            binding.apply {
                tvAcronym.text = "Acronym: "
                tvAcronymMore.text = acronym.lf
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcronymViewHolder {
        val view = ItemAcronymBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AcronymViewHolder((view))
    }

    override fun onBindViewHolder(holder: AcronymViewHolder, position: Int) {
        holder.bind(listAcronymsData[position])
    }

    override fun getItemCount(): Int = listAcronymsData.size

    interface OnItemClickCallback {
        fun onItemClicked(data: LfX)
    }
}
