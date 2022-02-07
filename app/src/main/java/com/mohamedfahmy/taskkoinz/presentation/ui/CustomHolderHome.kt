package com.mohamedfahmy.taskkoinz.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mohamedfahmy.taskkoinz.R
import com.mohamedfahmy.taskkoinz.data.model.Photo
import com.mohamedfahmy.taskkoinz.databinding.FragmentHomeRecyclerViewBinding

class CustomHolderHome :
    RecyclerView.Adapter<CustomHolderHome.MyViewHolder>() {


    private var item: MutableList<Photo> = ArrayList()
    private var oldItem: MutableList<Photo> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: FragmentHomeRecyclerViewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.fragment_home_recycler_view,
            parent,
            false
        )

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(item[position])
    }

    override fun getItemCount(): Int {
        return if (item.isEmpty()) 0 else item.size
    }


    fun setData(newItem: List<Photo>) {
        val oldList = ArrayList(item)
        item.clear()
        item.addAll(newItem)
        val result: DiffUtil.DiffResult = DiffUtil.calculateDiff(HomeDiffCallback(oldList, item))
        result.dispatchUpdatesTo(this)
    }

    private var onItemClickListener: ((Photo) -> Unit)? = null

    fun setOnItemClickListener(
        listener: (Photo) -> Unit
    ) {
        onItemClickListener = listener
    }

    inner class MyViewHolder(private val binding: FragmentHomeRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(data: Photo) {
            binding.item = data
            binding.executePendingBindings()

            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(data)
                }
            }
        }
    }
}


