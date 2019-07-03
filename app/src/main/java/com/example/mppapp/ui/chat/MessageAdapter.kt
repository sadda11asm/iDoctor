package com.example.mppapp.ui.chat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mppapp.R
import org.kotlin.mpp.mobile.data.entity.Message
import java.lang.IllegalArgumentException

class MessageAdapter(
    private val messages: List<Message>,
    private val context: Context
) : RecyclerView.Adapter<MessageAdapter.MessageHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageHolder {
        val layout = when (viewType) {
            MessageType.SENT_MESSAGE.type() -> R.layout.item_sent_message
            MessageType.RECEIVED_MESSAGE.type() -> R.layout.item_received_message
            else -> throw IllegalArgumentException("Did not match") // TODO refactor
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
        return if (position % 2 == 0) MessageType.SENT_MESSAGE.type() else MessageType.RECEIVED_MESSAGE.type()
    }

    override fun onBindViewHolder(holder: MessageHolder, position: Int) {
        holder.bind(messages[position])
    }

    override fun getItemCount() = messages.size


    inner class MessageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(message: Message) {
            //TODO implement
        }
    }
}