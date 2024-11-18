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

        // Set adapter for RecyclerView
        recyclerView.adapter = ItemAdapter(DataHolder.recommendedItems) { item: ItemObject ->
            DataHolder.savedItems.add(item)
            Toast.makeText(requireContext(), "${item.name} saved!", Toast.LENGTH_SHORT).show()
        }

        // Initialize and set onClickListener for Button
        val myButton: Button = view.findViewById(R.id.sortByPriceButton)
        myButton.setOnClickListener {
            Toast.makeText(requireContext(), "changed to by price", Toast.LENGTH_SHORT).show()
        }

        // Initialize and set onClickListener for Second Button
        val secondButton: Button = view.findViewById(R.id.sortByDistanceButton)
        secondButton.setOnClickListener {
            Toast.makeText(requireContext(), "changed to by distance", Toast.LENGTH_SHORT).show()
        }
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
