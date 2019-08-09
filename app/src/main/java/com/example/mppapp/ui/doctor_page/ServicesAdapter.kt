package com.example.mppapp.ui.doctor_page

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mppapp.R
import com.example.mppapp.model.ServiceO
import com.example.mppapp.util.BaseAdapter
import com.example.mppapp.util.BaseHolder
import io.ktor.http.parseServerSetCookieHeader

class ServicesAdapter(private val list: ArrayList<ServiceO>) : BaseAdapter<ServiceO>(list) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        val inflater = LayoutInflater.from(parent.context)

        return ServicesViewHolder(inflater, parent)

    }


    inner class ServicesViewHolder(inflater: LayoutInflater, parent: ViewGroup): BaseHolder(inflater.inflate(R.layout.item_service, parent, false)) {

        private var serviceName: TextView? = null
        private var servicePrice: TextView? = null

        init {
            serviceName = itemView.findViewById(R.id.service_name)
            servicePrice = itemView.findViewById(R.id.service_price)
        }

        override fun bind(position: Int) {
            val serviceO = list[position]
            serviceName?.text = serviceO.name
            servicePrice?.text = serviceO.pivot.price.toString() + " тг. "
        }


    }

}