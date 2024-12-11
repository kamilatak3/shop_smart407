package com.cs407.shopsmart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class CustomInfoWindowAdapter(context: Context) : GoogleMap.InfoWindowAdapter {

    private val window: View = LayoutInflater.from(context).inflate(R.layout.custom_info_window, null)

    override fun getInfoWindow(marker: Marker): View? {
        // Return null to use default frame, only customize the contents
        return null
    }

    override fun getInfoContents(marker: Marker): View {
        // Bind the title and snippet to the respective TextViews
        val titleTextView: TextView = window.findViewById(R.id.titleTextView)
        val addressTextView: TextView = window.findViewById(R.id.addressTextView)

        titleTextView.text = marker.title
        addressTextView.text = marker.snippet

        return window
    }
} 