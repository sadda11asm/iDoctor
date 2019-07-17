package com.example.mppapp.ui.doctor_page

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.mppapp.R
import com.example.mppapp.databinding.ActivityDoctorDetailsBinding
import com.example.mppapp.model.DoctorO
import com.example.mppapp.ui.chat.ChatActivity
import com.example.mppapp.util.getAccessToken
import com.example.mppapp.util.getName
import org.kotlin.mpp.mobile.ServiceLocator
import org.kotlin.mpp.mobile.data.entity.Doctor
import presentation.doctorpage.DoctorPagePresenter
import presentation.doctorpage.DoctorPageView

class DoctorDetailsActivity : AppCompatActivity(), DoctorPageView {


    private val presenter by lazy { ServiceLocator.doctorPagePresenter }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_details)

        presenter.attachView(this)

        val binding: ActivityDoctorDetailsBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_doctor_details
        )

        val doctor: DoctorO = intent.getSerializableExtra("doctor") as DoctorO
        binding.doctor = doctor

        binding.button.setOnClickListener{
            val title = getFullName() + "," + doctor.name
            Log.d("Details", title)
            startChat(token(), title, doctor.userId?.toInt()!!, false, doctor.id?.toInt()!!, doctor.avatar)
        }

        Glide
            .with(this)
            .load(doctor.imageLink)
            .error(R.drawable.default_avatar)
            .apply(RequestOptions.circleCropTransform())
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.iconDoctorDetail)

        title = doctor.name


    }

    override fun goToChat(chatId: Int, avatar: String) {
        Log.v("Details", "chatId: $chatId")
        ChatActivity.open(this, chatId, avatar)
    }
    override fun getFullName():String {
        return getName()
    }

    override fun token(): String {
        return getAccessToken()
    }

    override fun startChat(token:String, title:String, userId: Int, anonymous: Boolean, doctorId: Int, avatar: String) {
        presenter.createChat(token, title, userId, anonymous, doctorId, avatar)
    }

    override fun showError(e: Exception) {
        Log.v("Details", e.toString())
    }


    companion object {
        fun open(context: Context, doctor: DoctorO) {
            val intent = Intent(context, DoctorDetailsActivity::class.java)
            intent.putExtra("doctor", doctor)
            context.startActivity(intent)
        }
    }
}
