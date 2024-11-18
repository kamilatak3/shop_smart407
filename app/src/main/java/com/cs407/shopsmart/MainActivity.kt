package com.cs407.shopsmart

import ItemObject
import ShopObject
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        findViewById<Button>(R.id.nav_explore).setOnClickListener {
            fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ExplorePage::class.java, null)
                .setReorderingAllowed(true)
                .addToBackStack("Explore Button")
                .commit()
        }
        findViewById<Button>(R.id.nav_saved).setOnClickListener {
            fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SavedPage::class.java, null)
                .setReorderingAllowed(true)
                .addToBackStack("Saved Button")
                .commit()
        }
        findViewById<Button>(R.id.nav_recommend).setOnClickListener {
            fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RecommendPage::class.java, null)
                .setReorderingAllowed(true)
                .addToBackStack("Recommend Button")
                .commit()
        }
        findViewById<ImageView>(R.id.searchIcon).setOnClickListener {
            fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SearchPage::class.java, null)
                .setReorderingAllowed(true)
                .addToBackStack("Search Button")
                .commit()
        }

    }


}