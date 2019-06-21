package com.example.mppapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.mppapp.databinding.ActivityDoctorDetailsBinding
import com.example.mppapp.model.DoctorO
import com.example.mppapp.model.to
import kotlinx.android.synthetic.main.item_doctor_list.view.*
import kotlinx.coroutines.*
import org.koin.android.ext.android.bind
import org.kotlin.mpp.mobile.data.entity.Doctor
import java.io.Serializable
import kotlin.coroutines.CoroutineContext

class DoctorDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_details)

        val binding: ActivityDoctorDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_doctor_details)

        val doctor: DoctorO = intent.getSerializableExtra("doctor") as DoctorO

        binding.doctor = doctor

        Glide
            .with(this)
            .load(doctor.imageLink)
            .error(R.drawable.default_avatar)
            .apply(RequestOptions.circleCropTransform())
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.iconDoctorDetail)

        title = doctor.name



    }

    companion object {
        fun open(context: Context, doctor: DoctorO) {
            val intent = Intent(context, DoctorDetailsActivity::class.java)
            intent.putExtra("doctor", doctor)
            context.startActivity(intent)
        }
    }
}
