package com.example.mppapp.ui.chatlist

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.mppapp.R
import com.example.mppapp.util.*
import data.entity.Chat
import data.entity.LastMessage
import kotlinx.android.synthetic.main.item_chat_list.view.*
import kotlinx.android.synthetic.main.item_unread_message.view.*
import org.kotlin.mpp.mobile.data.entity.Message
import org.kotlin.mpp.mobile.util.log

class ChatAdapter(
    private val context: Context,
    private val itemClickListener: ItemClickListener<Chat>,
    private var chats: MutableList<Chat> = mutableListOf()
) : BaseAdapter<Chat>(chats) {

    private val userId = getUserId()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_chat_list, parent, false)
        return ChatHolder(view)
    }

    fun addChat(chat: Chat) {
        chats.add(0, chat)
        notifyItemInserted(0)
    }

    fun setData(newChats: MutableList<Chat>) {
        chats.clear()
        chats.addAll(newChats)
        notifyDataSetChanged()
    }

    fun updateMessage(mes: Message) {
        var updateIndex = -1
        var updateChat: Chat? = null
        for (i in 0 until chats.size) {
            if (chats[i].id == mes.chatId) {
                updateIndex = i
                updateChat = chats[i]
            }
        }
        updateChat?.lastMessage = LastMessage(mes.id, mes.text, mes.chatId, mes.userId, mes.createdAt, mes.createdAt)
        for (member in updateChat!!.members)
            if (member.userId == getUserId()) member.unreadCount++
        chats.removeAt(updateIndex)
        chats.add(0, updateChat)
        notifyItemMoved(updateIndex, 0)
//        notifyDataSetChanged()
    }


    inner class ChatHolder(itemView: View) : BaseHolder(itemView), View.OnClickListener {

        private lateinit var chat: Chat

        override fun bind(position: Int) = with(itemView) {
            chat = chats[position]

            val lastMessage = chat.lastMessage

            textMessage.text = when {
                lastMessage == null -> resources.getString(R.string.chat_list_no_messages)
                lastMessage.userId == userId -> resources.getString(R.string.chat_list_your_message, lastMessage.message)
                else -> lastMessage.message
            }

            if (chat.members.size > 2) {
                textChatName.text = chat.title
            } else {
                val names = chat.title!!.split(',')
                textChatName.text = if (names[0] == getName()) names[1] else names[0]
            }

            if(lastMessage != null) {
                val lastMessageSentTime = lastMessage.createdAt?.substring(5, 10)
                val time = lastMessageSentTime!!.split('-')
                textSentTime.text = "${time[1]}.${time[0]}"
            }

            for (member in chat.members) {
                if (userId == member.userId && member.unreadCount > 0) {
                    textMessageCount.visibility = View.VISIBLE
                    // TODO add 99+ if more than 99 messages
                    textMessageCount.text = member.unreadCount.toString()
                    textChatName.setTextColor(Color.parseColor("#1F8EFA")) // TODO refactor
                }
            }

            Glide
                .with(context)
                .load(chat.formattedAvatar)
                .error(R.drawable.ava)
                .apply(RequestOptions.circleCropTransform())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageAvatar)

            setOnClickListener {
                itemClickListener.onClick(chat)
            }
        }

        override fun onClick(view: View?) {
            itemClickListener.onClick(chat)
        }
    }
}