package com.example.mppapp.ui.doctors_list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.mppapp.R
import com.example.mppapp.util.ItemClickListener
import kotlinx.android.synthetic.main.item_doctor_card.view.*
import org.kotlin.mpp.mobile.data.entity.Doctor
import java.lang.IllegalArgumentException

class DoctorAdapter(
    private val doctors: MutableList<Doctor>,
    private val context: Context,
    private val itemClickListener: ItemClickListener<Doctor>
) : RecyclerView.Adapter<BaseHolder>() {

    private var isLoaderVisible = false


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        return when (viewType) {
            ViewType.LOADER.type() -> LoaderHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.item_loader, parent, false
                )
            )
            ViewType.DOCTOR.type() -> DoctorHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.item_doctor_card, parent, false
                )
            )
            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = doctors.size

    override fun getItemViewType(position: Int) =
        if (position == doctors.size - 1 && isLoaderVisible) ViewType.LOADER.type() else ViewType.DOCTOR.type()

    fun addLoader() {
        isLoaderVisible = true
        doctors.add(doctors[doctors.size - 1])
        notifyItemInserted(doctors.size - 1)
    }

    fun addItems(doctors: MutableList<Doctor>) {
        this.doctors.addAll(doctors)
        notifyItemRangeInserted(this.doctors.size, doctors.size)
    }

    fun removeLoader() {
        isLoaderVisible = false
        doctors.removeAt(doctors.size - 1)
        notifyItemRemoved(doctors.size)
    }

    inner class DoctorHolder(itemView: View) : BaseHolder(itemView), View.OnClickListener {

        private lateinit var doctor: Doctor

        override fun bind(position: Int) = with(itemView) {
            doctor = doctors[position]
            textName.text = doctor.name
            textSkills.text = doctor.specializations
            textExperience.text = "Стаж ${doctor.experience}" // TODO refactor
            textAddress.text = doctor.city.name

            // TODO move Glide to separate method
            Glide
                .with(context)
                .load(doctor.imageLink)
                .error(R.drawable.default_avatar)
                .apply(RequestOptions.circleCropTransform())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageAvatar)

            setOnClickListener {
                itemClickListener.onClick(doctor)
            }
        }

        override fun onClick(view: View?) {
            itemClickListener.onClick(doctor)
        }
    }

    inner class LoaderHolder(itemView: View) : BaseHolder(itemView)
}