package com.example.mppapp.ui.doctor_page

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.mppapp.R
import com.example.mppapp.databinding.ActivityDoctorDetailsBinding
import com.example.mppapp.model.DoctorO

class DoctorDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_details)

        val binding: ActivityDoctorDetailsBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_doctor_details
        )

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
