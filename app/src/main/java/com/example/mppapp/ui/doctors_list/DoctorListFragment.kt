package com.example.mppapp.ui.doctors_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mppapp.R
import com.example.mppapp.model.to
import com.example.mppapp.util.ItemClickListener
import com.example.mppapp.ui.doctor_page.DoctorDetailsActivity
import com.example.mppapp.util.getAccessToken
import kotlinx.android.synthetic.main.fragment_doctor_list.*
import kotlinx.coroutines.delay
import org.kotlin.mpp.mobile.ServiceLocator
import org.kotlin.mpp.mobile.data.entity.Doctor
import org.kotlin.mpp.mobile.data.entity.DoctorResponse
import org.kotlin.mpp.mobile.presentation.doctorlist.DoctorListView

class DoctorListFragment : Fragment(), DoctorListView, ItemClickListener<Doctor> {


    private val TAG = "DoctorListFragment"

    private val presenter by lazy { ServiceLocator.doctorListPresenter }

    private lateinit var adapter: DoctorAdapter

    private lateinit var layoutManager: LinearLayoutManager

    private var isFirstLoad = true

    private var isLastPage = false

    private var isLoading = false


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_doctor_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        activity?.title = resources.getString(R.string.search_doctor)
        swipeRefresh.setOnRefreshListener { presenter.refreshDoctors() }
    }

    override fun onClick(data: Doctor) {
        this.context?.let { DoctorDetailsActivity.open(it, data.to()) }
    }

    override fun token() = getAccessToken()

    /**
     * @param isFirstLoad distinguishes between actual firstLoad and
     * refreshing adapter with new data set
     */

    override fun showDoctors(doctorResponse: DoctorResponse) {
        if(isFirstLoad) {
            setupAdapter(doctorResponse)
            isFirstLoad = false
        } else {
            adapter.updateDataSet(doctorResponse.data)
            swipeRefresh.isRefreshing = false
        }
    }

    override fun showMoreDoctors(doctorResponse: DoctorResponse) {
        adapter.removeLoader()
        adapter.addItems(doctorResponse.data)
        isLoading = false
    }

    override fun showLoadFailed() {
        Toast.makeText(context, "Error while loading data", Toast.LENGTH_SHORT).show()
        adapter.removeLoader()
        isLoading = false
    }

    private fun setupAdapter(doctorResponse: DoctorResponse) {
        adapter = DoctorAdapter(doctorResponse.data, context!!, this)
        layoutManager = LinearLayoutManager(context)

        progressLoading.visibility = View.GONE

        recyclerDoctors.visibility = View.VISIBLE
        recyclerDoctors.layoutManager = layoutManager
        recyclerDoctors.adapter = adapter

        recyclerDoctors.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            override fun loadMoreItems() {
                isLoading = true
                adapter.addLoader()
                presenter.loadDoctors()
            }

            override fun isLoading() = isLoading

            override fun isLastPage() = isLastPage
        })
    }
}