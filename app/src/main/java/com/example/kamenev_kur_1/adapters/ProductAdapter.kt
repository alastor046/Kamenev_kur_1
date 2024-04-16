package com.example.kamenev_kur_1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kamenev_kur_1.Classes.Product
import com.example.kamenev_kur_1.Classes.Products
import com.example.kamenev_kur_1.R
import com.example.kamenev_kur_1.databinding.ListItemProductBinding

class ProductAdapter:ListAdapter<Product, ProductAdapter.Holder>(Comparator()) {
    class Holder(view:View): RecyclerView.ViewHolder(view){
        private val binding = ListItemProductBinding.bind(view)
        fun bind(product: Product)= with(binding){
            name.text = product.nameProduct
            price.text = product.priceProduct.toString()
            type.text = product.typeProduct
        }
    }
    class Comparator:DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.idProduct == newItem.idProduct

        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_product, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }


}