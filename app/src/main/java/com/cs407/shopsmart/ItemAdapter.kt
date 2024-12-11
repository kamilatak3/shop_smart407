package com.cs407.shopsmart

import ItemObject
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(
    private var items: MutableList<ItemObject>, // Dataset for the adapter
    private val onItemSave: (ItemObject) -> Unit // Callback for save button
) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemName: TextView = view.findViewById(R.id.itemName)
        val itemPrice: TextView = view.findViewById(R.id.itemPrice)
        val saveButton: Button = view.findViewById(R.id.saveButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.itemName.text = item.name
        holder.itemPrice.text = "$${item.price}"
        holder.saveButton.setOnClickListener { onItemSave(item) }
    }

    override fun getItemCount(): Int = items.size

    // Method to update items dynamically
    fun updateItems(newItems: List<ItemObject>) {
        items.clear() // Clear old items
        items.addAll(newItems) // Add new items
        notifyDataSetChanged() // Refresh RecyclerView
    }
}




