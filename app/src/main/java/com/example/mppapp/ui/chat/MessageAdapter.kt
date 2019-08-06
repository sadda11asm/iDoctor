package com.example.mppapp.ui.chat

import android.content.Context
import android.util.NoSuchPropertyException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mppapp.R
import com.example.mppapp.model.DateViewModel
import com.example.mppapp.model.MessageViewModel
import com.example.mppapp.model.ViewModel
import kotlinx.android.synthetic.main.item_date_message.view.*
import kotlinx.android.synthetic.main.item_sent_message.view.*

class MessageAdapter(
    private val context: Context,
    private val messages: MutableList<ViewModel>
) : RecyclerView.Adapter<BaseMessageHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseMessageHolder {
        val view = LayoutInflater.from(context).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.item_sent_message -> ReceivedMessageHolder(view) // TODO change
            R.layout.item_received_message -> ReceivedMessageHolder(view)
            R.layout.item_date_message -> DateMessageHolder(view)
            else -> throw NoSuchPropertyException("NoSuchViewType") // TODO refactor
        }
    }

    override fun getItemCount() = messages.size

    override fun getItemViewType(position: Int) = messages[position].type()

    override fun onBindViewHolder(holder: BaseMessageHolder, position: Int) {
        holder.bind(messages[position])
    }

    fun getLastMessageId(): Int {
        if (itemCount > 0) {
            val lastMessage = messages[itemCount - 1] as MessageViewModel
            return lastMessage.message.id
        }
        return -1
    }

    fun addMessage(message: MessageViewModel) {
        messages.add(message)
        notifyItemInserted(messages.size - 1)
    }

    open inner class ReceivedMessageHolder(itemView: View) : BaseMessageHolder(itemView) {
        override fun bind(item: ViewModel) = with(itemView) {
            val itemMessage = item as MessageViewModel
            textMessage.text = itemMessage.message.text
            textSentDate.text = itemMessage.message.createdAt?.substring(11, 16) // TODO better implementation?
        }
    }

    inner class DateMessageHolder(itemView: View) : BaseMessageHolder(itemView) {
        override fun bind(item: ViewModel) = with(itemView) {
            val itemDateMessage = item as DateViewModel
            textDate.text = itemDateMessage.formattedDate
        }
    }
}