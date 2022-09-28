package com.example.acronyms.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.acronyms.data.model.AcronymTestMoshiItem
import com.example.acronyms.data.model.LfX
import com.example.acronyms.databinding.ItemAcronymBinding

class AcronymAdapter : RecyclerView.Adapter<AcronymAdapter.AcronymViewHolder>() {
    private val listAcronymsData = ArrayList<AcronymTestMoshiItem>()
//    private var onItemClickCallback: OnItemClickCallback? = null

//    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
//        this.onItemClickCallback = onItemClickCallback
//    }

    fun setList(acronyms: AcronymTestMoshiItem) {
        listAcronymsData.clear()
        listAcronymsData.addAll(arrayOf())
        notifyDataSetChanged()
    }

    inner class AcronymViewHolder(val binding: ItemAcronymBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(acronym: AcronymTestMoshiItem) {
//            binding.root.setOnClickListener {
//                onItemClickCallback?.onItemClicked(acronym)
//            }
            binding.apply {
                tvAcronym.text = acronym.sf
                tvAcronymMore.text = acronym.toString()
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
