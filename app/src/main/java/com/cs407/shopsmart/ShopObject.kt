data class ShopObject(
    val id: Int,
    val name: String,
    val distance: Double,
    val items: List<ItemObject> // List of items for sale in this shop
)
