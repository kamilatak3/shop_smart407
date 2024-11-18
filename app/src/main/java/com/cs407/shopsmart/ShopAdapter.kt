package com.cs407.shopsmart

import ShopObject
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ShopAdapter(
    private val shops: List<ShopObject>,
    private val onShopClick: (ShopObject) -> Unit
) : RecyclerView.Adapter<ShopAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val shopName: TextView = view.findViewById(R.id.shopName)
        val shopDistance: TextView = view.findViewById(R.id.shopDistance)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shop, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shop = shops[position]
        holder.shopName.text = shop.name
        holder.shopDistance.text = "${shop.distance} miles"
        holder.itemView.setOnClickListener {
            onShopClick(shop)
        }
    }

    override fun getItemCount(): Int = shops.size
}

