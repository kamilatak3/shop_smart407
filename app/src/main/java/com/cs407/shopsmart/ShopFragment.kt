package com.cs407.shopsmart

import ItemObject
import ShopObject
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ShopFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ShopAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val shopList = generatePlaceholderData() // temporary data for development
        recyclerView = view.findViewById(R.id.shopRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = ShopAdapter(shopList) { shop ->
            // Use newInstance to create ItemFragment with the selected shop
            val fragment = ItemFragment.newInstance(shop)
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }
        recyclerView.adapter = adapter
    }

    private fun generatePlaceholderData(): List<ShopObject> {
        val shopList = listOf(
            ShopObject(
                id = 1,
                name = "Shop A",
                distance = 0.5,
                items = listOf(
                    ItemObject(id = 1, name = "Milk", shopLocation = "Shop A", price = 2.5),
                    ItemObject(id = 2, name = "Bread", shopLocation = "Shop A", price = 1.0)
                )
            ),
            ShopObject(
                id = 2,
                name = "Shop B",
                distance = 1.2,
                items = listOf(
                    ItemObject(id = 3, name = "Eggs", shopLocation = "Shop B",  price = 3.0),
                    ItemObject(id = 4, name = "Tomato", shopLocation = "Shop B", price = 1.5)
                )
            )
        )
        return shopList
    }
}



