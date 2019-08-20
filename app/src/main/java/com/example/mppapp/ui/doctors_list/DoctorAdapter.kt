package com.example.mppapp.ui.doctors_list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.mppapp.R
import com.example.mppapp.util.BaseHolder
import com.example.mppapp.util.ButtonChatClickListener
import com.example.mppapp.util.ItemClickListener
import com.example.mppapp.util.PageableAdapter
import kotlinx.android.synthetic.main.item_doctor_card.view.*
import org.kotlin.mpp.mobile.data.entity.Doctor
import java.lang.IllegalArgumentException
import java.util.*

class DoctorAdapter(
    private val context: Context,
    private val itemClickListener: ItemClickListener<Doctor>,
    private val buttonChatClickListener: ButtonChatClickListener<Doctor>,
    private var doctors: MutableList<Doctor> = mutableListOf()
) : PageableAdapter<Doctor>(doctors) {

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

    override fun getItemViewType(position: Int) =
        if (position == doctors.size - 1 && isLoaderVisible) ViewType.LOADER.type() else ViewType.DOCTOR.type()


    inner class DoctorHolder(itemView: View) : BaseHolder(itemView), View.OnClickListener {

        private lateinit var doctor: Doctor

        override fun bind(position: Int) = with(itemView) {
            doctor = doctors[position]
            textName.text = doctor.name
            textSpecializations.text = doctor.specializations
            textExperience.text = resources.getString(R.string.doctor_card_experience, doctor.experience)
            textQualification.text =
                if (doctor.qualifications.isNotEmpty()) doctor.qualifications[0].name else resources.getString(R.string.doctor_card_qualification)
            rating.rating = doctor.avgRate.toFloat()
            textRating.text = String.format(Locale.US, "%.1f", doctor.avgRate)
            textReviews.text = resources.getString(R.string.doctor_card_reviews, doctor.commentsCount)
            textMedcenter.text =
                if (doctor.medcenters.isNotEmpty() && doctor.medcenters[0].name.isNotEmpty()) {
                    doctor.medcenters[0].name
                } else {
                    resources.getString(R.string.doctor_card_no_medcenter)
                } // TODO refactor

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

            buttonChat.setOnClickListener {
                buttonChatClickListener.onButtonClick(doctor, position)
            }
        }

        fun showLoader() = with(itemView) {
            progressChat.visibility = View.VISIBLE
            buttonChat.text = null
            buttonChat.alpha = 0.7F
            buttonChat.isClickable = false
            buttonChat.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        }

        fun hideLoader() = with(itemView) {
            progressChat.visibility = View.GONE
            buttonChat.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_chats_white, 0, 0, 0)
            buttonChat.text = resources.getString(R.string.doctor_details_chat)
            buttonChat.alpha = 1.0F
            buttonChat.isClickable = true
        }

        override fun onClick(view: View?) {
            itemClickListener.onClick(doctor)
        }
    }

    inner class LoaderHolder(itemView: View) : BaseHolder(itemView)
}