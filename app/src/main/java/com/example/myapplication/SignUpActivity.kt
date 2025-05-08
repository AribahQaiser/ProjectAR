package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.ActivitySignUpBinding
import com.example.myapplication.datamodels.User
import com.example.myapplication.roomdatabase.AppDatabase
import com.example.myapplication.roomdatabase.UserDao
import kotlinx.coroutines.launch

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var db: AppDatabase
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = AppDatabase.getDatabase(applicationContext)
        userDao = db.userDao()
        initListener()
    }

    // init Listener
    private fun initListener() {
        binding.signinTxtv.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        binding.signUpButton.setOnClickListener {
            val username = binding.userNameInputField.text.toString().trim()
            val email = binding.emailInputField.text.toString().trim()
            val password = binding.passInputField.text.toString().trim()
//            val address = binding.editTextAddress.text.toString().trim()
            val address = "Rawalpindi"
            val phoneNumber = binding.phoneNumeInputField.text.toString().trim()

            if (username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && address.isNotEmpty() && phoneNumber.isNotEmpty()) {
                val user = User(
                    username = username,
                    email = email,
                    password = password,
                    address = address,
                    phoneNumber = phoneNumber
                ) // Remember to hash the password in a real app!
                lifecycleScope.launch {
                    userDao.insert(user)
                    Toast.makeText(
                        this@SignUpActivity,
                        "User registered successfully!",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish() // Go back to the login screen or navigate as needed
                }
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}