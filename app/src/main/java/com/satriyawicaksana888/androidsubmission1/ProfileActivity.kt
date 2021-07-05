package com.satriyawicaksana888.androidsubmission1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.satriyawicaksana888.androidsubmission1.data.User
import com.satriyawicaksana888.androidsubmission1.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_USER = "extra_user"
    }

    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User
        binding.civProfileImage.setImageResource(user.avatars)
        binding.civProfileImage.load(user.avatarUrl)
        binding.tvName.text = user.name
        binding.tvUsername.text = user.username
        binding.tvCompany.text = user.company
        binding.tvLocation.text = user.location
        binding.tvRepository.text = user.repository
        binding.tvFollowers.text = user.followers
        binding.tvFollowing.text = user.following
        binding.ibShare.setOnClickListener {
            val mIntent = Intent()
            mIntent.action = Intent.ACTION_SEND
            mIntent.putExtra(Intent.EXTRA_TEXT, "https://www.github.com/${binding.tvUsername.text}")
            mIntent.type = "text/plain"
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(mIntent)
            }
        }
    }
}