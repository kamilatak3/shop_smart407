package com.cs407.shopsmart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SearchPage : Fragment() {

    private lateinit var searchInput: EditText
    private lateinit var searchIcon: ImageView
    private lateinit var searchResults: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchInput = view.findViewById(R.id.search_input)
        searchIcon = view.findViewById(R.id.search_icon)
        searchResults = view.findViewById(R.id.search_results)

        searchResults.layoutManager = LinearLayoutManager(requireContext())
        searchResults.adapter = SearchAdapter(emptyList()) // Adapter with empty data initially

        searchIcon.setOnClickListener {
            val query = searchInput.text.toString().trim()
            if (query.isNotEmpty()) {
                performSearch(query)
            }
        }
    }

    private fun performSearch(query: String) {
        val results = listOf("Result 1", "Result 2", "Result 3") // Replace with actual search results
        searchResults.adapter = SearchAdapter(results)
    }
}
