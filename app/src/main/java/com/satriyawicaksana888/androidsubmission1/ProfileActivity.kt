package com.satriyawicaksana888.androidsubmission1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.google.android.material.tabs.TabLayoutMediator
import com.satriyawicaksana888.androidsubmission1.adapter.SectionPagerAdapter
import com.satriyawicaksana888.androidsubmission1.data.User
import com.satriyawicaksana888.androidsubmission1.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_USER = "extra_user"
        private val TAB_TITLES = intArrayOf(
            R.string.tab_title_1,
            R.string.tab_title_2,
        )
    }

    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User
        binding.civProfileImage.load(user.avatarUrl)
        binding.tvName.text = user.name
        binding.tvUsername.text = user.username
        binding.tvCompany.text = user.company
        binding.tvLocation.text = user.location
        binding.tvRepository.text = user.repository
        binding.ibShare.setOnClickListener {
            val mIntent = Intent()
            mIntent.action = Intent.ACTION_SEND
            mIntent.putExtra(Intent.EXTRA_TEXT, user.htmlUrl)
            mIntent.type = "text/plain"
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(mIntent)
            }
        }
        val sectionPageAdapter = user.username?.let { SectionPagerAdapter(this, username = it) }
        binding.vpFollow.adapter = sectionPageAdapter
        TabLayoutMediator(binding.tlFollow, binding.vpFollow) {
            tab, position -> tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

}
