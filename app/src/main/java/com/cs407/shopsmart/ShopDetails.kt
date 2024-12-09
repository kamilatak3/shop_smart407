import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
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

        // TODO: Load and display additional shop details using the shopId

    }
}