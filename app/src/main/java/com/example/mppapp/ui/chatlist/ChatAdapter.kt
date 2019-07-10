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
import java.text.SimpleDateFormat

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
            if (chat.title=="anonymous user" || chat.title =="anonym" || chat.title == null)
                chatName.text = "Анонимный юзер :)"
            else chatName.text = chat.title

            last_message.text = chat.lastMessage?.message ?: "Пока нет сообщений в этом чате"
            var lastMesTime = chat.lastMessage?.updatedAt
            if (lastMesTime == null)
                time.text = "Недавно"
            else {
                lastMesTime = lastMesTime.substring(0, 10)
                val arr = lastMesTime.split('-')
                time.text = arr[2]+'.'+arr[1]
            }

            setOnClickListener {
                itemClickListener.onClick(currentChat)
            }

            Glide
                .with(context)
                .load(R.drawable.idoctor)
                .centerCrop()
        }

        override fun onClick(view: View?) {
            itemClickListener.onClick(currentChat)
        }
    }
}