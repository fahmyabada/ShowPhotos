package com.mohamedfahmy.taskkoinz.presentation.ui

import androidx.recyclerview.widget.DiffUtil
import com.mohamedfahmy.taskkoinz.data.model.Photo

class HomeDiffCallback(private val oldItem : List<Photo>, private val newItem : List<Photo>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItem.size

    override fun getNewListSize(): Int = newItem.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItem[oldItemPosition].id == newItem[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItem[oldItemPosition] == newItem[newItemPosition]
    }
}