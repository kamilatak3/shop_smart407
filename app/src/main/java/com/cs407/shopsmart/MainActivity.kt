package com.cs407.shopsmart

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    companion object {
        const val CHANNEL_ID = "shop_smart_channel"
        const val NOTIFICATION_ID = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        createNotificationChannel()

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

    /**
     * Creates a Notification Channel for ShopSmart notifications.
     */
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "ShopSmart Channel"
            val descriptionText = "Channel for ShopSmart notifications"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}