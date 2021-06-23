package com.thesimplifiedapps.checknot.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thesimplifiedapps.checknot.databinding.ItemCheckNoteBinding
import com.thesimplifiedapps.checknot.viewmodels.data.CheckNot

class CheckNotAdapter(private var list: MutableList<CheckNot>) :
    RecyclerView.Adapter<CheckNotAdapter.CheckNotViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CheckNotViewHolder(
        ItemCheckNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: CheckNotViewHolder, position: Int) {
        list.run {
            holder.onBind(position)
        }
    }

    override fun getItemCount() = list.size

    inner class CheckNotViewHolder(private val binding: ItemCheckNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(position: Int) {

        }
    }
}