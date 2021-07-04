package com.satriyawicaksana888.androidsubmission1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.satriyawicaksana888.androidsubmission1.data.SearchUser
import com.satriyawicaksana888.androidsubmission1.databinding.ItemSearchBinding

class CardViewAdapter(private val listUser: ArrayList<SearchUser>) :
    RecyclerView.Adapter<CardViewAdapter.CardViewViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    inner class CardViewViewHolder(private val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: SearchUser) {
            with(binding) {
                civProfileImage.load(user.avatars)
                tvUsername.text = user.username
                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(user) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: SearchUser)
    }

    override fun getItemCount(): Int = listUser.size
}