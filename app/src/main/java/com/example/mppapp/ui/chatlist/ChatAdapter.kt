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
import data.entity.LastMessage
import kotlinx.android.synthetic.main.item_chat_list.view.*
import kotlinx.android.synthetic.main.item_unread_message.view.*
import org.kotlin.mpp.mobile.data.entity.Message
import org.kotlin.mpp.mobile.util.log

class ChatAdapter(
    private val context: Context,
    private val itemClickListener: ItemClickListener<Chat>,
    private var chats: MutableList<Chat> = mutableListOf()
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

    fun addChat(chat: Chat) {
        log("Sockets", "CHAT_ADDED")
        chats.add(0, chat)
        notifyItemInserted(0)
    }

    fun setData(newChats: MutableList<Chat>) {
        chats.clear()
        chats.addAll(newChats)
        log("Sockets-changed", newChats.toString())
        notifyDataSetChanged()
    }

    fun updateMessage(mes: Message) {
        log("Sockets-changed", mes.toString())
        log("Sockets", "PREV-CHATS: $chats")
        var updateIndex = -1
        var updateChat: Chat? = null
        for (i in 0 until chats.size) {
            if (chats[i].id == mes.chatId) {
                updateIndex = i
                updateChat = chats[i]
            }
        }
        log("Sockets", "INDEX: $updateIndex")
        updateChat?.lastMessage = LastMessage(mes.id, mes.text, mes.chatId, mes.userId, mes.createdAt, mes.createdAt)
        log("Sockets", "New Chat: $updateChat")
        for (member in updateChat!!.members)
            if (member.userId == getUserId()) member.unreadCount++
        chats.removeAt(updateIndex)
        chats.add(0, updateChat)
        log("Sockets", "NEW-CHATS: $chats")
        notifyItemMoved(updateIndex, 0)
//        notifyDataSetChanged()
    }



    inner class ChatHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        // TODO refactor
        private lateinit var currentChat: Chat

        fun bind(chat: Chat) = with(itemView) {
            currentChat = chat
            if (chat.title == "anonymous user" || chat.title == "anonym" || chat.title == null)
                textChatName.text = "Анонимный юзер :)"
            else {
                if (chat.members.size > 2) {
                    textChatName.text = chat.title
                } else {
                    val names = chat.title!!.split(',')
                    textChatName.text = if (names[0] == getName()) names[1] else names[0]
                }
            }
            for (member in chat.members)
                if (member.userId == getUserId()) {
                    if (member.unreadCount != 0) {
                        textMessageCount.visibility = View.VISIBLE
                        textMessageCount.text = member.unreadCount.toString()
                    } else {
                        textMessageCount.visibility = View.INVISIBLE
                    }
                    log("Sockets", "COUNT: ${member.unreadCount}")
                }

            var mes = if (chat.lastMessage?.userId == getUserId()) "Вы: "
            else ""

            if (chat.lastMessage != null)
                mes += chat.lastMessage!!.message
            else mes = "Пока нет сообщений в этом чате"

            textMessage.text = mes

            var lastMesTime = chat.lastMessage?.updatedAt
            if (lastMesTime == null)
                textSentTime.text = ""
            else {
                lastMesTime = lastMesTime.substring(0, 10)
                val arr = lastMesTime.split('-')
                textSentTime.text = arr[2] + "." + arr[1]
            }

            setOnClickListener {
                itemClickListener.onClick(currentChat)
            }

            log("AVATAR", chat.formattedAvatar)
            Glide
                .with(context)
                .load(chat.formattedAvatar)
                .error(R.drawable.ava)
                .apply(RequestOptions.circleCropTransform())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageAvatar)
        }

        override fun onClick(view: View?) {
            itemClickListener.onClick(currentChat)
        }
    }
}