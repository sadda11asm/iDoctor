package com.example.mppapp.ui.doctor_page

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.mppapp.R
import com.example.mppapp.databinding.ActivityDoctorDetailsBinding
import com.example.mppapp.model.DoctorO
import com.example.mppapp.model.ServiceO
import com.example.mppapp.ui.chat.ChatActivity
import com.example.mppapp.util.ProgressDialogFragment
import com.example.mppapp.util.getAccessToken
import com.example.mppapp.util.getName
import com.google.android.material.tabs.TabLayout
import com.r0adkll.slidr.Slidr
import org.kotlin.mpp.mobile.ServiceLocator
import org.kotlin.mpp.mobile.data.entity.Doctor
import presentation.doctorpage.DoctorPagePresenter
import presentation.doctorpage.DoctorPageView

class DoctorDetailsActivity : AppCompatActivity(), DoctorPageView {


    private val presenter  = ServiceLocator.doctorPagePresenter

    private lateinit var progressDialog: ProgressDialogFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_details)

        presenter.attachView(this)

        val binding: ActivityDoctorDetailsBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_doctor_details
        )

        val doctor: DoctorO = intent.getSerializableExtra("doctor") as DoctorO

        val adapter = DoctorPagerAdapter(doctor.services as ArrayList<ServiceO>, doctor.id.toInt(), this, supportFragmentManager)

        with(binding) {
            this.doctor = doctor



            doctorInfoContainer.adapter = adapter

            tabLayout.setupWithViewPager(doctorInfoContainer)

            startChat.setOnClickListener {
                val title = getFullName() + "," + doctor.name

                progressDialog = ProgressDialogFragment.show(supportFragmentManager)

                startChat(token(), title, doctor.userId?.toInt()!!, false, doctor.id.toInt(), doctor.imageLink)



            }

            title = doctor.name



        }




        Glide
            .with(this)
            .load(doctor.imageLink)
            .error(R.drawable.default_avatar)
            .into(binding.iconDoctorDetail)



    }

    override fun goToChat(chatId: Int, avatar: String, title: String?) {
        progressDialog.dismiss()
        ChatActivity.open(this, chatId, avatar, title?.split(',')?.get(1))
    }

    override fun getFullName(): String {
        return getName()
    }

    override fun token(): String {
        return getAccessToken()
    }

    override fun startChat(
        token: String,
        title: String,
        userId: Int,
        anonymous: Boolean,
        doctorId: Int,
        avatar: String
    ) {
        presenter.createChat(token, title, userId, anonymous, doctorId, avatar)
    }

    override fun showError(e: Exception) {
        Log.v("Details", e.toString())
        Toast.makeText(this, R.string.doctors_load_error_message, Toast.LENGTH_SHORT).show()
        progressDialog.dismiss()
    }


    companion object {
        fun open(context: Context, doctor: DoctorO) {
            val intent = Intent(context, DoctorDetailsActivity::class.java)
            intent.putExtra("doctor", doctor)
            context.startActivity(intent)
        }
    }
}
