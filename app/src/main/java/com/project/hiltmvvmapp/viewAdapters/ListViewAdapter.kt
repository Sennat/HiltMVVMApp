package com.project.hiltmvvmapp.viewAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.hiltmvvmapp.databinding.FruitsListItemBinding
import com.project.hiltmvvmapp.model.Fruit

class ListViewAdapter(
    private val listItems: MutableList<Fruit> = mutableListOf(),
) : RecyclerView.Adapter<ListViewAdapter.DashboardViewHolder>() {

    fun updateFruitData(newList: List<Fruit>) {
        listItems.clear()
        listItems.addAll(newList)
        notifyDataSetChanged()
    }

    inner class DashboardViewHolder(private val binding: FruitsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Fruit) {
            binding.apply {
                txtFruitName.text = item.name
                txtCarbValue.text = " ${item.nutritions.carbohydrates} g"
                txtProteinValue.text = " ${item.nutritions.protein} g"
                txtFatValue.text = " ${item.nutritions.fat} g"
                txtCaloriesValue.text = item.nutritions.calories.toString()
                txtSugarValue.text = " ${item.nutritions.sugar} g"
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        return DashboardViewHolder(
            FruitsListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        holder.onBind(listItems[position])
    }

    override fun getItemCount(): Int {
        return listItems.size
    }
}