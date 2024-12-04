package com.cs407.shopsmart

// Import statements
import ItemObject
import ShopObject
import Coordinates

// Coordinates class definition

object DataHolder {
    val recommendedItems: List<ItemObject> = listOf(
        ItemObject(id = 1, name = "Milk", shopLocation = "Shop A", price = 2.5),
        ItemObject(id = 2, name = "Bread", shopLocation = "Shop B", price = 1.0),
        ItemObject(id = 3, name = "Eggs", shopLocation = "Shop A", price = 3.0)
    )

    val shops: List<ShopObject> = listOf(
        ShopObject(
            id = 1,
            name = "Pick 'n Save",
            distance = 2.5, // You may want to calculate or assign appropriate distances
            address = "2502 Shopko Dr, Madison, WI 53704",
            coordinates = Coordinates(43.112868, -89.349744),
            items = emptyList() // Assuming empty for now
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
}