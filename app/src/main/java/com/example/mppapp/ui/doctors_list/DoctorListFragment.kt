package com.example.mppapp.ui.doctors_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mppapp.R
import com.example.mppapp.model.to
import com.example.mppapp.util.ItemClickListener
import com.example.mppapp.ui.doctor_page.DoctorDetailsActivity
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.fragment_doctor_list.*
import org.kotlin.mpp.mobile.ServiceLocator
import org.kotlin.mpp.mobile.data.entity.Doctor
import org.kotlin.mpp.mobile.data.entity.DoctorResponse
import org.kotlin.mpp.mobile.presentation.doctorlist.DoctorListView

class DoctorListFragment : Fragment(), DoctorListView, ItemClickListener<Doctor> {


    private val TAG = "DoctorListFragment"

    private val presenter by lazy { ServiceLocator.doctorListPresenter }

    private lateinit var adapter: DoctorAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_doctor_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        activity?.title = "Поиск врача" // TODO change hardcoded string
    }

    override fun onClick(data: Doctor) {
        Log.d(TAG, "onClick(doctor)")
        this.context?.let { DoctorDetailsActivity.open(it, data.to()) }
    }

    override fun showLoading() {
        Log.d(TAG, "showLoading()")
        presenter.onLoadDoctors(Hawk.get<String>("access_token")) // TODO replace with utils call
    }

    override fun showDoctors(doctorResponse: DoctorResponse) {
        Log.d(TAG, doctorResponse.toString())
        adapter = DoctorAdapter(doctorResponse.data, context!!, this)
        recyclerDoctors.layoutManager = LinearLayoutManager(context)
        recyclerDoctors.adapter = adapter
    }

    override fun showLoadFailed(e: Exception) {
        Log.d(TAG, "ERROR ${e.message}")
    }

}