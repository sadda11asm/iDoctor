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
            textSkills.text = doctor.skills[0].name // TODO display all skills
            textAddress.text = doctor.city.name
            textMedcenter.text = "" // TODO get medcenter name or remove from XML

            val url = "https://cabinet.idoctor.kz${doctor.avatar}"
            Log.d("URL", url)

            // TODO add separate method to load image
            Glide
                .with(context)
                .load(url)
                .apply(RequestOptions.circleCropTransform())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageAvatar)
        }

        override fun onClick(view: View?) {
            itemClickListener.onClick(currentDoctor)
        }
    }
}