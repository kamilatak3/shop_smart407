package com.cs407.shopsmart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(
    private var products: List<ProductDetail>
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productImageView: ImageView = view.findViewById(R.id.productImageView)
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

        // Dynamically set the image based on imageName
        val context: Context = holder.itemView.context
        val imageResId = context.resources.getIdentifier(
            product.imageName, "drawable", context.packageName
        )
        if (imageResId != 0) {
            holder.productImageView.setImageResource(imageResId)
        } else {
            // Set a default image if the specific product image is not found
            holder.productImageView.setImageResource(R.drawable.ic_default_image)
        }
    }

    override fun getItemCount(): Int = products.size

    fun updateProducts(newProducts: List<ProductDetail>) {
        products = newProducts
        notifyDataSetChanged()
    }
} 