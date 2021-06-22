package com.satriyawicaksana888.androidsubmission1.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.satriyawicaksana888.androidsubmission1.ProfileActivity
import com.satriyawicaksana888.androidsubmission1.data.User
import com.satriyawicaksana888.androidsubmission1.databinding.ItemGithubUserBinding

class CardViewAdapter(private val listUser: ArrayList<User>, private val context: Context) :
    RecyclerView.Adapter<CardViewAdapter.CardViewViewHolder>() {
    inner class CardViewViewHolder(private val binding: ItemGithubUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            with(binding) {
                civProfileImage.setImageResource(user.avatars)
                tvUsername.text = user.username
                tvCompany.text = user.company
                tvRepository.text = user.repository
                tvFollowers.text = user.followers
                tvFollowing.text = user.following
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val binding =
            ItemGithubUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        holder.bind(listUser[position])
        holder.itemView.setOnClickListener {
            val mIntent = Intent(context, ProfileActivity::class.java)
            mIntent.putExtra(ProfileActivity.EXTRA_USER, listUser[position])
            context.startActivity(mIntent)
        }
    }

    override fun getItemCount(): Int = listUser.size
}