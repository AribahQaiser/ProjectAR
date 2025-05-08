package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.roomdatabase.AppDatabase
import com.example.myapplication.roomdatabase.UserDao
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: AppDatabase
    private lateinit var userDao: UserDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.rootLayout)
        db = AppDatabase.getDatabase(applicationContext)
        userDao = db.userDao()
        // Observe all users (for demonstration)
        lifecycleScope.launch {
            userDao.getAllUsers().collectLatest { users ->
                Log.d("LoginActivity", "All Users: $users")
                // You can process the list of users here if needed
            }
        }
        initListener()
    }

    // Init Listener
    private fun initListener() {
        binding.userSignupTxtv.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        binding.signInButton.setOnClickListener {
            val username = binding.emailInputLayout.editText?.text?.trim().toString()
            val password = binding.passInputLayout.editText?.text?.trim().toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                lifecycleScope.launch {
                    val user = userDao.getUserByUsername(username)
                    if (user != null && user.password == password) { // In a real app, compare hashed passwords!
                        Toast.makeText(this@MainActivity, "Login successful!", Toast.LENGTH_SHORT)
                            .show()
                        startActivity(Intent(this@MainActivity, DashBoardActivity::class.java))
                        finish()
                        // Navigate to your main app screen here
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "Invalid username or password",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}