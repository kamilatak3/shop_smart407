data class Coordinates(val latitude: Double, val longitude: Double)


data class ShopObject(
    val id: Int,
    val name: String,
    val distance: Double,
    val address: String,
    val coordinates: Coordinates,
    val items: List<ItemObject> // List of items for sale in this shop
)
