package com.example.mppapp.ui.chat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mppapp.R
import kotlinx.android.synthetic.main.item_sent_message.view.*
import org.kotlin.mpp.mobile.data.entity.Message
import java.lang.IllegalArgumentException

class MessageAdapter(
    private val context: Context,
    private val messages: MutableList<Message>,
    private val userId: Int
) : RecyclerView.Adapter<MessageAdapter.MessageHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageHolder {
        val layout = when (viewType) {
            MessageType.SENT_MESSAGE.type() -> R.layout.item_sent_message
            MessageType.RECEIVED_MESSAGE.type() -> R.layout.item_received_message
            else -> throw IllegalArgumentException("Did not match")
        }
        return MessageHolder(
            LayoutInflater.from(context).inflate(
                layout,
                parent,
                false
            )
        )
    }

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].userId == userId) MessageType.SENT_MESSAGE.type() else MessageType.RECEIVED_MESSAGE.type()
    }

    override fun onBindViewHolder(holder: MessageHolder, position: Int) {
        holder.bind(messages[position])
    }

    override fun getItemCount() = messages.size

    fun addItem(message: Message) {
        messages.add(message)
        notifyItemInserted(messages.size - 1)
    }

    inner class MessageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(message: Message) = with(itemView) {
            textMessage.text = message.text
            textSentDate.text = message.createdAt?.substring(11, 16) // TODO better implementation?
        }
    }
}