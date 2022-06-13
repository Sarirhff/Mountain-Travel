package com.example.mountaintravel.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.mountaintravel.databinding.ActivityNotificationBinding
import com.example.mountaintravel.databinding.FragmentHomeBinding

class NotificationActivity : AppCompatActivity() {

    private lateinit var rvNotif : RecyclerView
    private val list = ArrayList<Mountain>()
    private var dbinding :  ActivityNotificationBinding? = null
    private val binding get () = dbinding

    override fun onCreate(savedInstanceState: Bundle?) {
        dbinding = ActivityNotificationBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding!!.root)

        supportActionBar!!.title = "Notification"


    }
}