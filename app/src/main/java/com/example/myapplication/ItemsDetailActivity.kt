package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.adapter.FurnitureAdapter
import com.example.myapplication.databinding.ActivityItemsDetailBinding
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.datamodels.FurnitureItem
import com.google.gson.Gson
import java.io.InputStream

class ItemsDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityItemsDetailBinding
    private lateinit var adapter: FurnitureAdapter

    private var isSofa: Boolean = false;
    lateinit var inputStream: InputStream

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

         isSofa = intent.getBooleanExtra("is_sofa", false) // default is false


        // Init Adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 1)
        val itemList = readItemsFromFile()

        adapter = FurnitureAdapter(itemList,
            onItemClick = { item ->
                Toast.makeText(this, "Item clicked: ${item.name}", Toast.LENGTH_SHORT).show()
            },
            onButtonClick = { item ->
//                startActivity(Intent(this, ARActivity::class.java))
                val intent = Intent(this@ItemsDetailActivity, ARActivity::class.java)
                intent.putExtra("is_sofa", isSofa) // or false depending on condition
                startActivity(intent)
                Toast.makeText(this, "Button clicked: ${item.name}", Toast.LENGTH_SHORT).show()
            }
        )

        binding.recyclerView.adapter = adapter

    }

    // Read items from file
    private fun readItemsFromFile(): List<FurnitureItem> {

        if (isSofa) {
            inputStream = resources.openRawResource(R.raw.sofa_items)
        } else inputStream = resources.openRawResource(R.raw.bed_items)

        val jsonString = inputStream.bufferedReader().use { it.readText() }
        return Gson().fromJson(jsonString, Array<FurnitureItem>::class.java).toList()
    }
}