package com.cs407.shopsmart

import ShopObject
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ItemFragment : Fragment(R.layout.fragment_item) {

    companion object {
        private const val ARG_SHOP_ID = "shop_id"
        private const val ARG_SHOP_NAME = "shop_name"
        private const val ARG_SHOP_DISTANCE = "shop_distance"

        fun newInstance(shop: ShopObject): ItemFragment {
            val fragment = ItemFragment()
            val args = Bundle().apply {
                putInt(ARG_SHOP_ID, shop.id)
                putString(ARG_SHOP_NAME, shop.name)
                putDouble(ARG_SHOP_DISTANCE, shop.distance)
                // Note: You can pass additional data if needed
            }
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve the shop data from the arguments
        val shopId = arguments?.getInt(ARG_SHOP_ID)
        val shopName = arguments?.getString(ARG_SHOP_NAME)
        val shopDistance = arguments?.getDouble(ARG_SHOP_DISTANCE)

        // Example: Display the shop name in a TextView
        view.findViewById<TextView>(R.id.shopNameTextView)?.text = shopName
    }
}

