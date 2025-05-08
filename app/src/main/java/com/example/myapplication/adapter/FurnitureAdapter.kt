package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.datamodels.FurnitureItem

class FurnitureAdapter(
    private val items: List<FurnitureItem>,
    private val onItemClick: (FurnitureItem) -> Unit,
    private val onButtonClick: (FurnitureItem) -> Unit // new lambda
    ) : RecyclerView.Adapter<FurnitureAdapter.FurnitureViewHolder>() {

        inner class FurnitureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val name = itemView.findViewById<TextView>(R.id.item_name_txtv)
            val price = itemView.findViewById<TextView>(R.id.item_price_txtv)
            val ratingBar = itemView.findViewById<RatingBar>(R.id.item_rating_br)
            val imageView = itemView.findViewById<ImageView>(R.id.item_icon_imgv)
            val ViewInAR = itemView.findViewById<TextView>(R.id.view_in_ar_txtv) // make sure you have this

            fun bind(item: FurnitureItem) {
                name.text = item.name
                price.text = item.price
                ratingBar.rating = item.rating.toFloat()

                val resId = itemView.context.resources.getIdentifier(
                    item.imageName.substringBeforeLast('.'),
                    "drawable",
                    itemView.context.packageName
                )
                imageView.setImageResource(resId)

                itemView.setOnClickListener { onItemClick(item) }
                ViewInAR.setOnClickListener { onButtonClick(item) } // handle button click
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FurnitureViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.items_detail_layout, parent, false)
            return FurnitureViewHolder(view)
        }

        override fun onBindViewHolder(holder: FurnitureViewHolder, position: Int) {
            holder.bind(items[position])
        }

        override fun getItemCount() = items.size
    }