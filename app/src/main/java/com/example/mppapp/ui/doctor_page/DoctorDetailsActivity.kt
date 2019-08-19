package com.example.mppapp.ui.doctor_page

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.mppapp.R
import com.example.mppapp.model.DoctorO
import com.example.mppapp.model.ServiceO
import com.example.mppapp.ui.chat.ChatActivity
import com.example.mppapp.util.CloseableActivity
import com.example.mppapp.util.getAccessToken
import com.example.mppapp.util.getName
import kotlinx.android.synthetic.main.activity_doctor_details.*
import org.kotlin.mpp.mobile.ServiceLocator
import presentation.doctorpage.DoctorPageView
import java.util.*
import kotlin.collections.ArrayList

class DoctorDetailsActivity : CloseableActivity(R.layout.activity_doctor_details), DoctorPageView {

    private val presenter = ServiceLocator.doctorPagePresenter

    private lateinit var doctor: DoctorO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayShowTitleEnabled(false)

        doctor = intent.getSerializableExtra(EXTRA_DOCTOR) as DoctorO

        showDoctorInfo()

        val adapter = DoctorPagerAdapter(doctor.services as ArrayList<ServiceO>, doctor.id.toInt(), this, supportFragmentManager)

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

        buttonChat.setOnClickListener {
            val title = getFullName() + "," + doctor.name
            startChat(token(), title, doctor.userId?.toInt()!!, false, doctor.id.toInt(), doctor.imageLink)
        }

    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun getFullName() = getName()

    override fun token() = getAccessToken()

    override fun showDoctorInfo() {
        textName.text = doctor.name
        textSpecializations.text = doctor.specializations
        textExperience.text = resources.getString(R.string.doctor_details_experience, doctor.experience)
        textReviews.text = resources.getString(R.string.doctor_details_reviews, doctor.commentsCount)
        textQualification.text =
            if (doctor.qualifications.isNotEmpty()) doctor.qualifications[0].name else resources.getString(R.string.doctor_card_qualification)
        rating.rating = doctor.avgRate.toFloat()
        textRating.text = String.format(Locale.US, "%.1f", doctor.avgRate)

        Glide
            .with(this)
            .load(doctor.imageLink)
            .error(R.drawable.default_avatar)
            .into(imageAvatar)
    }

    override fun goToChat(chatId: Int, avatar: String, title: String?) {
        ChatActivity.open(this, chatId, avatar, title?.split(',')?.get(1))
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

    override fun showCreationError(e: Exception) {
        Log.v("Details", e.toString())
        Toast.makeText(this, R.string.doctors_load_error_message, Toast.LENGTH_SHORT).show()
    }


    companion object {
        const val EXTRA_DOCTOR = "extra_doctor"

        fun open(context: Context, doctor: DoctorO) {
            val intent = Intent(context, DoctorDetailsActivity::class.java)
            intent.putExtra(EXTRA_DOCTOR, doctor)
            context.startActivity(intent)
        }
    }
}
