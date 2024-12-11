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
        render(marker, window)
        return window
    }

    override fun getInfoContents(marker: Marker): View? {
        // Use default info window contents
        return null
    }

    private fun render(marker: Marker, view: View) {
        val title = marker.title
        val snippet = marker.snippet

        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val addressTextView: TextView = view.findViewById(R.id.addressTextView)

        titleTextView.text = title
        addressTextView.text = snippet
    }
} 