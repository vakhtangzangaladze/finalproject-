package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductAdapter(private val list: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val image: ImageView = view.findViewById(R.id.product_image)
        private val name: TextView = view.findViewById(R.id.product_name)
        private val description: TextView = view.findViewById(R.id.product_description)
        private val price: TextView = view.findViewById(R.id.product_price)
        private val addedBy: TextView = view.findViewById(R.id.product_author)

        fun init(item: Product) {
            name.text = item.name
            description.text = item.description
            price.text = "${item.price} ₾"
            addedBy.text = "დაამატა: ${item.addedBy}"
            Glide.with(image).load(item.image).into(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.product, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.init(item)
    }

    override fun getItemCount(): Int = list.size
}