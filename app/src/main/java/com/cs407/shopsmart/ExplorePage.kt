package com.cs407.shopsmart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
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
            googleMap.addMarker(
                MarkerOptions()
                    .position(shopLocation)
                    .title(shop.name)
                    .snippet(shop.address)
            )
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
    }
} 