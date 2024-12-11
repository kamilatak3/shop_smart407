package com.cs407.shopsmart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class ShopDetails : Fragment() {

    private var shopId: String? = null
    private lateinit var shopNameTextView: TextView
    private lateinit var shopAddressTextView: TextView
    private lateinit var shopDistanceTextView: TextView
    private lateinit var productsRecyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            shopId = it.getString("SHOP_ID")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shop_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shopNameTextView = view.findViewById(R.id.shopNameTextView)
        shopAddressTextView = view.findViewById(R.id.shopAddressTextView)
        shopDistanceTextView = view.findViewById(R.id.shopDistanceTextView)
        productsRecyclerView = view.findViewById(R.id.productsRecyclerView)

        productsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        productAdapter = ProductAdapter(emptyList())
        productsRecyclerView.adapter = productAdapter

        // Display basic shop details from DataHolder
        val shop = DataHolder.shops.find { it.id.toString() == shopId }
        shop?.let {
            shopNameTextView.text = it.name
            shopAddressTextView.text = it.address
            shopDistanceTextView.text = "${it.distance} miles"
        }

        // Fetch products from the server
        shopId?.let { fetchProducts(it) }
    }

    /**
     * Fetches products for the given shopId from the server.
     */
    private fun fetchProducts(shopId: String) {
        // Construct the URL. Replace with your actual endpoint.
        // Assuming you have an endpoint like /api/shop/{shopId}/products
        val url = "http://10.0.2.2:3000/api/shop/$shopId/products"

        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Handle failure on the UI thread
                requireActivity().runOnUiThread {
                    Toast.makeText(requireContext(), "Failed to load products: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) {
                        requireActivity().runOnUiThread {
                            Toast.makeText(requireContext(), "Error: ${response.message}", Toast.LENGTH_LONG).show()
                        }
                        return
                    }

                    val responseBody = it.body?.string()
                    if (responseBody != null) {
                        try {
                            val jsonArray = JSONArray(responseBody)
                            val products = mutableListOf<ProductDetail>()
                            for (i in 0 until jsonArray.length()) {
                                val jsonObject = jsonArray.getJSONObject(i)
                                val product = ProductDetail(
                                    id = jsonObject.getInt("id"),
                                    name = jsonObject.getString("name"),
                                    quantity = jsonObject.getInt("quantity"),
                                    price = jsonObject.getDouble("price"),
                                    imageName = getImageName(jsonObject.getString("name"))
                                )
                                products.add(product)
                            }

                            // Update RecyclerView on the UI thread
                            requireActivity().runOnUiThread {
                                productAdapter.updateProducts(products)
                            }
                        } catch (e: Exception) {
                            requireActivity().runOnUiThread {
                                Toast.makeText(requireContext(), "Failed to parse products", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }
            }
        })
    }

    private fun getImageName(productName: String): String {
        return when (productName.lowercase()) {
            "apples" -> "apples"
            "bananas" -> "bananas"
            "oranges" -> "oranges"
            "milk" -> "milk"
            "bread" -> "bread"
            "eggs" -> "eggs"
            "cheese" -> "cheese"
            "butter" -> "butter"
            "chicken" -> "chicken"
            "beef" -> "beef"
            "pasta" -> "pasta"
            "rice" -> "rice"
            "cereal" -> "cereal"
            "yogurt" -> "yogurt"
            "juice" -> "juice"
            else -> "ic_default_image" // Default image
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(shopId: String) =
            ShopDetails().apply {
                arguments = Bundle().apply {
                    putString("SHOP_ID", shopId)
                }
            }
    }
}