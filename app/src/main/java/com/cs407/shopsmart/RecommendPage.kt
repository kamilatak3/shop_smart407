package com.cs407.shopsmart

import ItemObject
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RecommendPage : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var adapter: ItemAdapter // Declare adapter as a class-level property

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recommend, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.recommendRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Initialize the adapter with a MutableList
        adapter = ItemAdapter(DataHolder.recommendedItems.toMutableList()) { item: ItemObject ->
            DataHolder.savedItems.add(item)
            Toast.makeText(requireContext(), "${item.name} saved!", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = adapter

        // Initialize and set onClickListener for Sort by Price Button
        val sortByPriceButton: Button = view.findViewById(R.id.sortByPriceButton)
        sortByPriceButton.setOnClickListener {
            sortByPrice()
        }

        // Initialize and set onClickListener for Sort by Distance Button
        val sortByDistanceButton: Button = view.findViewById(R.id.sortByDistanceButton)
        sortByDistanceButton.setOnClickListener {
            sortByDistance()
        }
    }

    /**
     * Sort recommended items by price and update RecyclerView.
     */
    private fun sortByPrice() {
        val sortedItems = DataHolder.recommendedItems.sortedBy { it.price }
        adapter.updateItems(sortedItems.toMutableList()) // Convert to MutableList
        Toast.makeText(requireContext(), "Items sorted by price", Toast.LENGTH_SHORT).show()
    }

    /**
     * Sort recommended items by shop distance and update RecyclerView.
     */
    private fun sortByDistance() {
        val sortedItems = DataHolder.recommendedItems.sortedBy { item ->
            DataHolder.shops.find { shop -> shop.name == item.shopLocation }?.distance ?: Double.MAX_VALUE
        }
        adapter.updateItems(sortedItems.toMutableList()) // Convert to MutableList
        Toast.makeText(requireContext(), "Items sorted by distance", Toast.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecommendPage().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
