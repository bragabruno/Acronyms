package com.example.acronyms.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.acronyms.data.model.AcronymList
import com.example.acronyms.databinding.ItemAcronymBinding

class AcronymAdapter : RecyclerView.Adapter<AcronymAdapter.AcronymViewHolder>() {

    private val listAcronymsData = mutableListOf<List<AcronymList>>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setList(acronyms: List<AcronymList>) {
        listAcronymsData.clear()
        listAcronymsData.addAll(listOf(acronyms))
        notifyDataSetChanged()
    }

    inner class AcronymViewHolder(private val binding: ItemAcronymBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(acronym: List<AcronymList>) {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(acronym)
            }
            binding.apply {
                tvAcronym.text = "Acronym:"
                tvAcronymMore.text = acronym.first().lfs.subList(0, 7).first().lf
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
        fun onItemClicked(data: List<AcronymList>)
    }
}
