import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.cs407.shopsmart.DataHolder
import com.cs407.shopsmart.R

class ShopDetails : Fragment() {

    private var shopId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            shopId = it.getString("SHOP_ID")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shop_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Display the shop ID at the top of the page
        val shopIdTextView: TextView = view.findViewById(R.id.shopIdTextView)
        shopIdTextView.text = "Shop ID: $shopId"

        // Display additional shop details
        val shopNameTextView: TextView = view.findViewById(R.id.shopNameTextView)
        val shopAddressTextView: TextView = view.findViewById(R.id.shopAddressTextView)
        val shopDistanceTextView: TextView = view.findViewById(R.id.shopDistanceTextView)

        val shop = DataHolder.shops.find { it.id.toString() == shopId }
        shop?.let {
            shopNameTextView.text = it.name
            shopAddressTextView.text = it.address
            shopDistanceTextView.text = "${it.distance} miles"
            // You can also set up the RecyclerView to display items if available
        }
    }
}