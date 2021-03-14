package com.example.section9roomdbdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.section9roomdbdemo.databinding.ListItemSubscriberBinding
import com.example.section9roomdbdemo.db.Subcribers

class SubscriberRecyclerViewAdapter(private var onItemClickListener: (Subcribers)->Unit) : RecyclerView.Adapter<SubscriberViewHolder>() {

    private val subcribers  = arrayListOf<Subcribers>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriberViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemSubscriberBinding.inflate(inflater,parent,false)
        return SubscriberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubscriberViewHolder, position: Int) {
        holder.onBind(subcribers[position],onItemClickListener)
    }

    override fun getItemCount(): Int {
        return subcribers.size
    }

    fun addAllSub(subcribers: List<Subcribers>){
        this.subcribers.clear()
        this.subcribers.addAll(subcribers)
        notifyDataSetChanged()
    }
}

class SubscriberViewHolder(private val binding: ListItemSubscriberBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(subcribers: Subcribers, onItemClickListener: (Subcribers) -> Unit) {
        binding.textViewName.text = subcribers.name
        binding.textViewMail.text = subcribers.mail
        binding.root.setOnClickListener {
            onItemClickListener.invoke(subcribers)
        }
    }
}