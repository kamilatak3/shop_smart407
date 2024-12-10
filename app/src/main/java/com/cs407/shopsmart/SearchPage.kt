import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cs407.shopsmart.DataHolder
import com.cs407.shopsmart.ItemAdapter
import com.cs407.shopsmart.MainActivity
import com.cs407.shopsmart.R
//import com.cs407.shopsmart.RecommendPage
import okhttp3.*
import org.json.JSONArray
import java.io.IOException

class SearchPage : Fragment() {

    private lateinit var searchInput: EditText
    private lateinit var searchIcon: ImageView
    private lateinit var searchResults: RecyclerView
    private lateinit var itemAdapter: ItemAdapter
    private val client = OkHttpClient()
    private var itemList: MutableList<ItemObject> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchInput = view.findViewById(R.id.search_input)
        searchIcon = view.findViewById(R.id.search_icon)
        searchResults = view.findViewById(R.id.search_results)

        // Initialize RecyclerView and Adapter
        itemAdapter = ItemAdapter(mutableListOf()) { item ->
            saveItem(item) // Callback for saving items
        }
        searchResults.layoutManager = LinearLayoutManager(requireContext())
        searchResults.adapter = itemAdapter

        // Fetch initial items
        fetchItems()

        // Set up search button click listener
        searchIcon.setOnClickListener {
            val query = searchInput.text.toString().trim()
            if (query.isNotEmpty()) {
                performSearch(query)
            } else {
                Toast.makeText(requireContext(), "Enter a search term", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fetchItems() {
        val request = Request.Builder()
            .url("http://10.0.2.2:3000/api/products") // Replace with your API endpoint
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                requireActivity().runOnUiThread {
                    Toast.makeText(requireContext(), "Failed to fetch items: ${e.message}", Toast.LENGTH_LONG).show()
                    Log.e("FetchError", "Failed to fetch items", e)
                }
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (response.isSuccessful) {
                        val responseBody = it.body?.string()
                        if (!responseBody.isNullOrEmpty()) {
                            val items = parseItems(JSONArray(responseBody))
                            requireActivity().runOnUiThread {
                                itemList.clear()
                                itemList.addAll(items)
                                Toast.makeText(requireContext(), "Items loaded successfully", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        requireActivity().runOnUiThread {
                            Toast.makeText(requireContext(), "Error: ${response.message}", Toast.LENGTH_LONG).show()
                        }
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

        requireActivity().runOnUiThread {
            if (results.isEmpty()) {
                Toast.makeText(requireContext(), "No items found for \"$query\"", Toast.LENGTH_SHORT).show()
            }
            itemAdapter.updateItems(results)
        }
    }

    private fun saveItem(item: ItemObject) {
        // Logic for saving items (e.g., to database or shared preferences)
        DataHolder.savedItems.add(item)
        Toast.makeText(requireContext(), "${item.name} saved!", Toast.LENGTH_SHORT).show()
        sendSaveNotification(item)
    }

    private fun sendSaveNotification(item: ItemObject) {
        val intent = Intent(requireContext(), MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            requireContext(),
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(requireContext(), MainActivity.CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_bookmark_add_24)
            .setContentTitle("Item Saved")
            .setContentText("${item.name} has been added to your saved items.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(requireContext())) {
            notify(MainActivity.NOTIFICATION_ID, builder.build())
        }
    }
}