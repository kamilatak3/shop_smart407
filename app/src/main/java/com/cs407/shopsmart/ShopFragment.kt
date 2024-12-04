package com.cs407.shopsmart

import ItemObject
import Coordinates
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
        return listOf(
            ShopObject(
                id = 1,
                name = "Pick 'n Save",
                distance = 2.5, // You may want to calculate or assign appropriate distances
                address = "2502 Shopko Dr, Madison, WI 53704",
                coordinates = Coordinates(43.0515, -89.3860),
                items = emptyList() // Assuming empty for now
            ),
            ShopObject(
                id = 2,
                name = "Pick 'n Save",
                distance = 3.0,
                address = "3650 University Ave, Madison, WI 53705",
                coordinates = Coordinates(43.1042, -89.3975),
                items = emptyList()
            ),
            ShopObject(
                id = 3,
                name = "Walmart",
                distance = 4.5,
                address = "2151 Royal Ave, Monona, WI 53713",
                coordinates = Coordinates(43.0740, -89.2723),
                items = emptyList()
            ),
            ShopObject(
                id = 4,
                name = "Walmart",
                distance = 5.0,
                address = "4198 Nakoosa Trail, Madison, WI 53714",
                coordinates = Coordinates(43.1090, -89.2965),
                items = emptyList()
            ),
            ShopObject(
                id = 5,
                name = "Festival Foods",
                distance = 1.5,
                address = "810 E Washington Ave, Madison, WI 53703",
                coordinates = Coordinates(43.0731, -89.3987),
                items = emptyList()
            ),
            ShopObject(
                id = 6,
                name = "Target",
                distance = 2.0,
                address = "750 Hilldale Wy, Madison, WI 53705",
                coordinates = Coordinates(43.0825, -89.3978),
                items = emptyList()
            ),
            ShopObject(
                id = 7,
                name = "Target",
                distance = 1.0,
                address = "610 State St, Madison, WI 53703",
                coordinates = Coordinates(43.0742, -89.3901),
                items = emptyList()
            ),
            ShopObject(
                id = 8,
                name = "Target",
                distance = 3.5,
                address = "4301 Lien Rd, Madison, WI 53704",
                coordinates = Coordinates(43.0518, -89.3859),
                items = emptyList()
            )
        )
    }    
}



