package com.example.kamenev_kur_1.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.kamenev_kur_1.Classes.Product
import com.example.kamenev_kur_1.Classes.Products
import com.example.kamenev_kur_1.R

class ProductAdapterLV(context: Context,
    private val dataSource: List<Product>): BaseAdapter() {
    override fun getCount(): Int {
        return dataSource.size
    }

    private val inflater = context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getItem(position: Int): Any {
        return  dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return dataSource[position].idProduct!!.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemView =inflater.inflate(R.layout.list_item_product, parent,false)
        val tvName: TextView = itemView.findViewById(R.id.name)
        val tvPrice: TextView = itemView.findViewById(R.id.price)
        val tvType: TextView = itemView.findViewById(R.id.type)

        var product = getItem(position) as Product

        tvName.text = product.nameProduct
        tvPrice.text = product.priceProduct.toString()
        tvType.text = product.typeProduct

        return itemView
    }


}