package com.satriyawicaksana888.androidsubmission1.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.satriyawicaksana888.androidsubmission1.ui.followers.FollowersFragment
import com.satriyawicaksana888.androidsubmission1.ui.following.FollowingFragment

class SectionPagerAdapter(appCompatActivity: AppCompatActivity, private val username: String): FragmentStateAdapter(appCompatActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        val mBundle = Bundle()
        when (position) {
            0 -> {
                val followersFragment = FollowersFragment()
                mBundle.putString(FollowersFragment.EXTRA_USERNAME, username)
                followersFragment.arguments = mBundle
                fragment = followersFragment
            }
            1 -> {
                val followingFragment = FollowingFragment()
                mBundle.putString(FollowingFragment.EXTRA_USERNAME, username)
                followingFragment.arguments = mBundle
                fragment = followingFragment
            }
        }
        return fragment as Fragment
    }
}