package com.example.mppapp.ui.doctors_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mppapp.R
import com.example.mppapp.model.to
import com.example.mppapp.ui.chat.ChatActivity
import com.example.mppapp.ui.doctor_page.DoctorDetailsActivity
import com.example.mppapp.util.*
import kotlinx.android.synthetic.main.fragment_doctor_list.*
import org.kotlin.mpp.mobile.ServiceLocator
import org.kotlin.mpp.mobile.data.entity.Doctor
import org.kotlin.mpp.mobile.presentation.doctorlist.DoctorListView

class DoctorListFragment : Fragment(), DoctorListView, ItemClickListener<Doctor>,
    ButtonChatClickListener<Doctor> {

    private val presenter by lazy { ServiceLocator.doctorListPresenter }

    private val adapter by lazy { DoctorAdapter(context!!, this, this) }

    private val layoutManager by lazy { LinearLayoutManager(context) }

    private var isLastPage = false

    private var isLoading = false


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_doctor_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        swipeRefresh.setOnRefreshListener { presenter.refreshDoctors() }
        presenter.attachView(this)
        presenter.start()
    }

    override fun onResume() {
        super.onResume()
        presenter.attachView(this)
        swipeRefresh.isRefreshing = false
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun onClick(data: Doctor) {
        this.context?.let { DoctorDetailsActivity.open(it, data.to()) }
    }

    override fun onChatButtonClick(item: Doctor, position: Int) {
        AlertDialog.Builder(context!!, R.style.ChatCreateAlertDialog)
            .setView(R.layout.dialog_chat_create)
            .setPositiveButton(R.string.doctor_list_chat_dialog_positive) { _, _ -> onChatCreate(item, position) }
            .setNegativeButton(R.string.doctor_list_chat_dialog_negative) { dialog, _ -> dialog.dismiss() }
            .show()
    }

    override fun token() = getAccessToken()

    override fun isConnectedToNetwork() = getNetworkConnection(activity)

    override fun showDoctors(doctors: MutableList<Doctor>) {
        adapter.addItems(doctors)
    }

    override fun showRefreshedDoctors(doctors: MutableList<Doctor>) {
        recyclerDoctors.visibility = View.VISIBLE
        textError.visibility = View.GONE
        swipeRefresh.isRefreshing = false
        adapter.updateDataSet(doctors)
    }

    override fun showRefreshingFailed() {
        swipeRefresh.isRefreshing = false
        Toast.makeText(context, R.string.toast_load_error_message, Toast.LENGTH_SHORT).show()
    }

    override fun hideLoading() {
        progressLoading.visibility = View.GONE
        recyclerDoctors.visibility = View.VISIBLE
    }

    override fun hidePaging() {
        adapter.removeLoader()
        isLoading = false
    }

    override fun showNoConnection() {
        progressLoading.visibility = View.GONE
        textError.visibility = View.VISIBLE
        textError.text = resources.getString(R.string.doctors_no_connection)
    }

    override fun showLoadingFailed() {
        progressLoading.visibility = View.GONE
        textError.visibility = View.VISIBLE
        textError.text = resources.getString(R.string.doctors_load_error_message)
    }

    override fun showPagingFailed() {
        isLoading = false
        adapter.removeLoader()
        Toast.makeText(context, R.string.toast_load_error_message, Toast.LENGTH_SHORT).show()
    }

    override fun showChatCreateLoader(position: Int) {
        val viewHolder = recyclerDoctors.findViewHolderForAdapterPosition(position) as DoctorAdapter.DoctorHolder
        viewHolder.showLoader()
    }

    override fun hideChatCreateLoader(position: Int) {
        val viewHolder = recyclerDoctors.findViewHolderForAdapterPosition(position) as DoctorAdapter.DoctorHolder
        viewHolder.hideLoader()
    }

    override fun showChatCreationError(e: Exception) {
        Toast.makeText(context, R.string.doctor_details_creation_error, Toast.LENGTH_SHORT).show()
    }

    override fun openChat(chatId: Int, avatar: String, title: String?) {
        ChatActivity.open(context!!, chatId, avatar, title?.split(',')?.get(1))
    }

    private fun setupRecycler() {
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

    private fun onChatCreate(item: Doctor, position: Int) {
        val title = getName() + "," + item.name
        presenter.createChat(token(), title, item.userId?.toInt()!!, false, item.id.toInt(), item.imageLink, position)
    }
}