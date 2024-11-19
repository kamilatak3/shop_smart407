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

class SavedPage : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_shopping_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.savedRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Pass saved items to the adapter
        recyclerView.adapter = ItemAdapter(DataHolder.savedItems) { item: ItemObject ->
            // Remove item when clicked
            DataHolder.savedItems.remove(item)
            recyclerView.adapter?.notifyDataSetChanged()
            Toast.makeText(requireContext(), "${item.name} removed!", Toast.LENGTH_SHORT).show()
        }

        // Initialize Clear All Button
        val clearAllButton: Button = view.findViewById(R.id.clearAllButton)
        clearAllButton.setOnClickListener {
            if (DataHolder.savedItems.isNotEmpty()) {
                DataHolder.savedItems.clear()
                recyclerView.adapter?.notifyDataSetChanged()
                Toast.makeText(requireContext(), "All items cleared!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "No items to clear.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SavedPage().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}