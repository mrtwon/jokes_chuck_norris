package com.mrtwon.humorcucknoris

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    lateinit var navContolerr: NavController
    lateinit var bottom_nav: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_nav = findViewById(R.id.bottom_nav_view)
        bottom_nav.setOnNavigationItemSelectedListener(this)
        navContolerr = Navigation.findNavController(this, R.id.nav_controller)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.joke -> {
                navContolerr.navigate(R.id.fragmentJokes)
            }
            R.id.web -> {
                navContolerr.navigate(R.id.fragmentWebView)
            }
        }
        return true
    }
}