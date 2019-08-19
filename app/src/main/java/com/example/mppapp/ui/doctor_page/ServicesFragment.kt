package com.example.mppapp.ui.doctor_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mppapp.R
import com.example.mppapp.model.ServiceO
import kotlinx.android.synthetic.main.fragment_services.*

class ServicesFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_services, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val services : ArrayList<ServiceO> = arguments!!.getSerializable("services") as ArrayList<ServiceO>
        setServices(services)
    }

    private fun setServices(services: ArrayList<ServiceO>) {
        servicesList.isNestedScrollingEnabled = true
        servicesList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ServicesAdapter(services)
        }
    }

}
