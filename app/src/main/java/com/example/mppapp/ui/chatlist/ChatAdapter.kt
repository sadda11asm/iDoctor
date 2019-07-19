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
import com.example.mppapp.util.ItemClickListener
import com.example.mppapp.util.getName
import com.example.mppapp.util.getUserId
import data.entity.Chat
import kotlinx.android.synthetic.main.item_chat_list.view.*

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
                chatNameView.text = "Анонимный юзер :)"
            else {
                if (chat.members.size>2) {
                    chatNameView.text = chat.title
                } else {
                    val names = chat.title!!.split(',')
                    chatNameView.text = if (names[0] == getName()) names[1] else names[0]
                }
            }
            for (member in chat.members)
                if (member.userId == getUserId()) {
                    if (member.unreadCount!=0)
                        unreadCountView.text = member.unreadCount.toString()
                    else unreadCountView.visibility = View.INVISIBLE

                }


            var mes = if (chat.lastMessage?.userId == getUserId()) "Вы: "
            else ""

            if (chat.lastMessage!=null)
                mes+= chat.lastMessage!!.message
            else mes = "Пока нет сообщений в этом чате"

            lastMessageView.text = mes

            var lastMesTime = chat.lastMessage?.updatedAt
            if (lastMesTime == null)
                time.text = ""
            else {
                lastMesTime = lastMesTime.substring(0, 10)
                val arr = lastMesTime.split('-')
                time.text = arr[2]+ "." + arr[1]
            }

            setOnClickListener {
                itemClickListener.onClick(currentChat)
            }

            Glide
                .with(context)
                .load(chat.avatar)
                .error(R.drawable.ava)
                .apply(RequestOptions.circleCropTransform())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageCountView)
        }

        override fun onClick(view: View?) {
            itemClickListener.onClick(currentChat)
        }
    }
}