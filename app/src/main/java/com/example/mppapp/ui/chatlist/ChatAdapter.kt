package com.example.mppapp.ui.chatlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.mppapp.R
import com.example.mppapp.ui.doctors_list.DoctorAdapter
import com.example.mppapp.util.ItemClickListener
import data.entity.Chat
import kotlinx.android.synthetic.main.item_chat_list.view.*
import kotlinx.android.synthetic.main.item_doctor_list.view.*
import org.kotlin.mpp.mobile.data.entity.Doctor

class ChatAdapter(
    private val chats: List<Chat>,
    private val context: Context,
    private val itemClickListener: ItemClickListener<Chat>
) : RecyclerView.Adapter<ChatAdapter.ChatHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHolder {
        return ChatHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_chat_list,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ChatHolder, position: Int) {
        holder.bind(chats[position])
    }

    override fun getItemCount() = chats.size


    inner class ChatHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private lateinit var currentChat: Chat

        fun bind(chat: Chat) = with(itemView) {
            currentChat = chat
            chatName.text = chat.title


            setOnClickListener {
                itemClickListener.onClick(currentChat)
            }

            Glide
                .with(context)
                .load(R.drawable.default_avatar)
        }

        override fun onClick(view: View?) {
            itemClickListener.onClick(currentChat)
        }
    }
}