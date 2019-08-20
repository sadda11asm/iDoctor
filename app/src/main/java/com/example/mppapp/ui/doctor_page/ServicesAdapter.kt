package com.example.mppapp.ui.doctor_page

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mppapp.R
import com.example.mppapp.model.ServiceO
import com.example.mppapp.util.BaseAdapter
import com.example.mppapp.util.BaseHolder
import kotlinx.android.synthetic.main.item_service.view.*

class ServicesAdapter(private val list: ArrayList<ServiceO>) : BaseAdapter<ServiceO>(list) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_service, parent, false)
        return ServiceViewHolder(view)
    }

    inner class ServiceViewHolder(itemView: View) : BaseHolder(itemView) {

        override fun bind(position: Int) = with(itemView) {
            val service = list[position]
            val price = service.pivot.price ?: 0
            textServicePrice.text = resources.getString(R.string.service_price, price)
            textServiceName.text = service.name
        }
    }

}