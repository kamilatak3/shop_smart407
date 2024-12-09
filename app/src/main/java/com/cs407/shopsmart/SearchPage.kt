import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cs407.shopsmart.R
import com.cs407.shopsmart.SearchAdapter
import okhttp3.*
import org.json.JSONArray
import java.io.IOException

class SearchPage : Fragment() {

    private lateinit var searchInput: EditText
    private lateinit var searchIcon: ImageView
    private lateinit var searchResults: RecyclerView
    private val client = OkHttpClient()
    private var itemList: List<ItemObject> = emptyList() // Use ShopObject here

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
        searchResults.adapter = SearchAdapter(emptyList()) // Start with empty data

        fetchItems()

        searchIcon.setOnClickListener {
            val query = searchInput.text.toString().trim()
            if (query.isNotEmpty()) {
                performSearch(query)
            }
        }
    }

    private fun fetchItems() {
        val request = Request.Builder()
            .url("http://10.0.2.2:3000/api/products") // Replace with your actual endpoint
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                requireActivity().runOnUiThread {
//                    val fullError = e.stackTraceToString()
                    Toast.makeText(requireContext(), "Failed to fetch items: ${e.message}", Toast.LENGTH_LONG).show()
                    Log.e("FetchError", "Failed to fetch items", e)
                }
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseBody = response.body?.string()
                    if (responseBody != null) {
                        itemList = parseItems(JSONArray(responseBody)) // Parse the JSON response
                        requireActivity().runOnUiThread {
                            Toast.makeText(requireContext(), "Items loaded successfully", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    requireActivity().runOnUiThread {
                        Toast.makeText(requireContext(), "Error: ${response.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

    private fun parseItems(jsonArray: JSONArray): List<ItemObject> {
        val items = mutableListOf<ItemObject>()
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val item = ItemObject(
                id = jsonObject.getInt("id"),
                name = jsonObject.getString("type"),
                price = jsonObject.getDouble("price")
            )
            items.add(item)
        }
        return items
    }

    private fun performSearch(query: String) {
        val results = itemList.filter { item ->
            item.name.contains(query, ignoreCase = true)
        }

        if (results.isEmpty()) {
            searchResults.adapter = SearchAdapter(listOf("No items found for \"$query\""))
        } else {
            searchResults.adapter = SearchAdapter(results.map { "${it.name} - ($${it.price})" })
        }
    }
}
