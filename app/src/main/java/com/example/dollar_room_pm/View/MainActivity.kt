package com.example.dollar_room_pm.View

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.dollar_room_pm.R
import com.example.dollar_room_pm.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)


          val firstFragment = FirstFragment()

        if( savedInstanceState == null){
            // 🔹 Agregamos el Fragment al layout usando commitNow() para que sea sincrónico

            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_main,firstFragment)
                .commitNow()

        }


    }





}