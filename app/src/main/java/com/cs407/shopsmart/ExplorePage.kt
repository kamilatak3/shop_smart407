package com.cs407.shopsmart

import ShopDetails
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class ExplorePage : Fragment(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        // Example: Add markers for each shop
        DataHolder.shops.forEach { shop ->
            val shopLocation = LatLng(shop.coordinates.latitude, shop.coordinates.longitude)
            val marker = googleMap.addMarker(
                MarkerOptions()
                    .position(shopLocation)
                    .title(shop.name)
                    .snippet(shop.address)
            )
            marker?.tag = shop.id  // Set the shop ID as the marker's tag
        }

        // Move the camera to the first shop or a default location
        if (DataHolder.shops.isNotEmpty()) {
            val firstShop = DataHolder.shops[0]
            val firstLocation = LatLng(firstShop.coordinates.latitude, firstShop.coordinates.longitude)
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstLocation, 12f))
        } else {
            // Default location (e.g., Madison, WI)
            val defaultLocation = LatLng(43.0731, -89.4012)
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 12f))
        }

        // Set custom info window adapter
        googleMap.setInfoWindowAdapter(CustomInfoWindowAdapter())

        // Set listeners
        setMapListeners()
    }

    private fun openShopDetails(shopId: String?) {
        val shopDetailsFragment = ShopDetails()
        val args = Bundle()
        args.putString("SHOP_ID", shopId)  // Pass the shop ID to the fragment
        shopDetailsFragment.arguments = args
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, shopDetailsFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setMapListeners() {
        googleMap.setOnMarkerClickListener { marker ->
            marker.showInfoWindow()
            true
        }

        // Handle info window clicks to open ShopDetails
        googleMap.setOnInfoWindowClickListener { marker ->
            val shopId = marker.tag as? Int
            openShopDetails(shopId?.toString())
        }

        // Optionally, handle map touch events to hide info windows
        googleMap.setOnMapClickListener {
            googleMap.clear()
            // Re-add markers if necessary
        }
    }

    inner class CustomInfoWindowAdapter : GoogleMap.InfoWindowAdapter {
        private val window: View = LayoutInflater.from(context).inflate(R.layout.custom_info_window, null)

        override fun getInfoWindow(marker: Marker): View? {
            return null // Use default frame
        }

        override fun getInfoContents(marker: Marker): View {
            val storeNameTextView: TextView = window.findViewById(R.id.storeNameTextView)
            storeNameTextView.text = marker.title
            return window
        }
    }
}