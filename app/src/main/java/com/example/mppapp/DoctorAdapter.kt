package com.example.mppapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_doctor_list.view.*
import org.kotlin.mpp.mobile.data.entity.Doctor
import java.lang.Error
import java.lang.Exception

class DoctorAdapter(
    private val doctors: List<Doctor>,
    private val context: Context,
    private val itemClickListener: ItemClickListener<Doctor>
) : RecyclerView.Adapter<DoctorAdapter.DoctorHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorHolder {
        return DoctorHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_doctor_list,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DoctorHolder, position: Int) {
        holder.bind(doctors[position])
    }

    override fun getItemCount() = doctors.size


    inner class DoctorHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private lateinit var currentDoctor: Doctor

        fun bind(doctor: Doctor) = with(itemView) {
            currentDoctor = doctor
            textName.text = doctor.name
            textSkills.text = doctor.specializations
            textExperience.text = "Стаж ${doctor.experience}"
            textAddress.text = doctor.city.name


            setOnClickListener {
                itemClickListener.onClick(currentDoctor)
            }

            Glide
                .with(context)
                .load(doctor.imageLink)
                .error(R.drawable.default_avatar)
                .apply(RequestOptions.circleCropTransform())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageAvatar)
        }

        override fun onClick(view: View?) {
            itemClickListener.onClick(currentDoctor)
        }
    }
}