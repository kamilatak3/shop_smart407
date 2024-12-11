package com.cs407.shopsmart

// Import statements
import ItemObject
import ShopObject
import Coordinates
import android.content.ClipData.Item

// Coordinates class definition

object DataHolder {
    private val apple = ItemObject(1, "Apples", 2.0) // Adjust price as needed
    private val banana = ItemObject(2, "Bananas", 1.5) // Adjust price as needed
    private val orange = ItemObject(3, "Oranges", 2.5) // Adjust price as needed

    // Dairy
    private val milk = ItemObject(4, "Milk", 2.5)
    private val eggs = ItemObject(5, "Eggs", 3.0) // Adjust price as needed
    private val cheese = ItemObject(6, "Cheese", 4.5) // Adjust price as needed
    private val butter = ItemObject(7, "Butter", 5.0) // Adjust price as needed
    private val yogurt = ItemObject(8, "Yogurt", 3.5) // Adjust price as needed

    // Meat
    private val chicken = ItemObject(9, "Chicken", 7.0) // Adjust price as needed
    private val beef = ItemObject(10, "Beef", 12.0) // Adjust price as needed

    // Pantry
    private val bread = ItemObject(11, "Bread", 2.5) // Adjust price as needed
    private val pasta = ItemObject(12, "Pasta", 3.0) // Adjust price as needed
    private val rice = ItemObject(13, "Rice", 4.0) // Adjust price as needed
    private val cereal = ItemObject(14, "Cereal", 5.5) // Adjust price as needed
    private val juice = ItemObject(15, "Juice", 3.5) // Adjust price as needed
    val recommendedItems: List<ItemObject> = listOf(
        apple, banana, orange, milk, eggs, cheese, butter, yogurt, chicken, beef, bread, pasta, rice, cereal, juice
    )

    val shops: List<ShopObject> = listOf(
        ShopObject(
            id = 1,
            name = "Pick 'n Save",
            distance = 2.5,
            address = "2502 Shopko Dr, Madison, WI 53704",
            coordinates = Coordinates(43.112868, -89.349744),
            items = emptyList()
        ),
        ShopObject(
            id = 2,
            name = "Pick 'n Save",
            distance = 3.0,
            address = "3650 University Ave, Madison, WI 53705",
            coordinates = Coordinates(43.076530, -89.449753),
            items = emptyList()
        ),
        ShopObject(
            id = 3,
            name = "Walmart",
            distance = 4.5,
            address = "2151 Royal Ave, Monona, WI 53713",
            coordinates = Coordinates(43.042474, -89.351415),
            items = emptyList()
        ),
        ShopObject(
            id = 4,
            name = "Walmart",
            distance = 5.0,
            address = "4198 Nakoosa Trail, Madison, WI 53714",
            coordinates = Coordinates(43.110821, -89.312660),
            items = emptyList()
        ),
        ShopObject(
            id = 5,
            name = "Festival Foods",
            distance = 1.5,
            address = "810 E Washington Ave, Madison, WI 53703",
            coordinates = Coordinates(43.0814796, -89.375034),
            items = emptyList()
        ),
        ShopObject(
            id = 6,
            name = "Target",
            distance = 2.0,
            address = "750 Hilldale Wy, Madison, WI 53705",
            coordinates = Coordinates(43.073800, -89.453216),
            items = emptyList()
        ),
        ShopObject(
            id = 7,
            name = "Target",
            distance = 1.0,
            address = "610 State St, Madison, WI 53703",
            coordinates = Coordinates(43.0749954, -89.3960469),
            items = emptyList()
        ),
        ShopObject(
            id = 8,
            name = "Target",
            distance = 3.5,
            address = "4301 Lien Rd, Madison, WI 53704",
            coordinates = Coordinates(43.1209739, -89.3102433),
            items = emptyList()
        )
    )

    val savedItems: MutableList<ItemObject> = mutableListOf()
//    val searchableItems: List<ItemObject> = listOf(

//    )
}
