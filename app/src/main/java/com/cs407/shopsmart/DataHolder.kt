package com.cs407.shopsmart

import ItemObject
import ShopObject

object DataHolder {
    val recommendedItems: List<ItemObject> = listOf(
        ItemObject(id = 1, name = "Milk", shopLocation = "Shop A", price = 2.5),
        ItemObject(id = 2, name = "Bread", shopLocation = "Shop B", price = 1.0),
        ItemObject(id = 3, name = "Eggs", shopLocation = "Shop A", price = 3.0)
    )

    val shops: List<ShopObject> = listOf(
        ShopObject(
            id = 1,
            name = "Shop A",
            distance = 0.5,
            items = listOf(
                ItemObject(id = 1, name = "Milk", shopLocation = "Shop A", price = 2.5),
                ItemObject(id = 3, name = "Eggs", shopLocation = "Shop A", price = 3.0)
            )
        ),
        ShopObject(
            id = 2,
            name = "Shop B",
            distance = 1.0,
            items = listOf(
                ItemObject(id = 2, name = "Bread", shopLocation = "Shop B", price = 1.0),
                ItemObject(id = 4, name = "Tomato", shopLocation = "Shop B", price = 1.5)
            )
        )
    )

    val savedItems: MutableList<ItemObject> = mutableListOf()
}
