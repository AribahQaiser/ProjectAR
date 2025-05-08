package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.ActivityDashBoardBinding
import com.example.myapplication.views.fragments.CartFragment
import com.example.myapplication.views.fragments.HomeFragment
import com.example.myapplication.views.fragments.ProfileFragment

class DashBoardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashBoardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(HomeFragment()) // Load the default fragment

        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.homeFragment -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.cartFragment -> {
                    loadFragment(CartFragment())
                    true
                }
                R.id.profileFragment -> {
                    loadFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}