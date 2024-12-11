package com.cs407.shopsmart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(
    private var products: List<ProductDetail>
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productNameTextView: TextView = view.findViewById(R.id.productNameTextView)
        val productQuantityTextView: TextView = view.findViewById(R.id.productQuantityTextView)
        val productPriceTextView: TextView = view.findViewById(R.id.productPriceTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product_detail, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.productNameTextView.text = product.name
        holder.productQuantityTextView.text = "Quantity: ${product.quantity}"
        holder.productPriceTextView.text = "Price: \$${String.format("%.2f", product.price)}"
    }

    override fun getItemCount(): Int = products.size

    fun updateProducts(newProducts: List<ProductDetail>) {
        products = newProducts
        notifyDataSetChanged()
    }
} 