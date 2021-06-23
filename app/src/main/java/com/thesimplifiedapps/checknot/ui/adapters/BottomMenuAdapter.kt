package com.thesimplifiedapps.checknot.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.thesimplifiedapps.checknot.MyApplication
import com.thesimplifiedapps.checknot.databinding.ItemBottomMenuSelectionBinding

class BottomMenuAdapter(
    private val colorList: List<Int>,
    private var selectedColor: Int,
    private val isFontColor: Boolean,
    private val onItemClick: (color: Int, isFont: Boolean) -> Unit
) :
    RecyclerView.Adapter<BottomMenuAdapter.BottomMenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BottomMenuViewHolder(
        ItemBottomMenuSelectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: BottomMenuViewHolder, position: Int) {
        colorList.run {
            holder.onBind(position)
        }
    }

    override fun getItemCount() = colorList.size

    fun setSelectedColor(color: Int) {
        selectedColor = color
        notifyDataSetChanged()
    }

    inner class BottomMenuViewHolder(private val binding: ItemBottomMenuSelectionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(position: Int) {
            val color =
                ContextCompat.getColor(MyApplication.myApplicationContext, colorList[position])
            itemView.tag = color
            if (isFontColor) {
                binding.textColorSelector.setTextColor(color)
                binding.textColorSelector.visibility = View.VISIBLE
                binding.backgroundColorSelector.visibility = View.GONE
            } else {
                binding.backgroundColorDisplay.backgroundTintList =
                    (ContextCompat.getColorStateList(
                        MyApplication.myApplicationContext,
                        colorList[position]
                    ))
                binding.backgroundColorSelector.visibility = View.VISIBLE
                binding.backgroundColorDisplay.visibility = View.VISIBLE
            }
            if (color == selectedColor) {
                if (!binding.backgroundColorSelector.isVisible) {
                    binding.backgroundColorSelector.visibility = View.VISIBLE
                }
                binding.selectedColor.visibility = View.VISIBLE
            } else if (binding.selectedColor.isVisible) {
                binding.selectedColor.visibility = View.GONE
            }
            itemView.setOnClickListener {
                onItemClick(it.tag as Int, isFontColor)
            }
        }
    }
}