package com.satriyawicaksana888.androidsubmission1.ui.following

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.satriyawicaksana888.androidsubmission1.R
import com.satriyawicaksana888.androidsubmission1.adapter.CardViewAdapter
import com.satriyawicaksana888.androidsubmission1.databinding.FollowingFragmentBinding
import com.satriyawicaksana888.androidsubmission1.ui.followers.FollowersFragment

class FollowingFragment : Fragment() {

    companion object {
        const val EXTRA_USERNAME = "extra_username_following"
    }

    private lateinit var viewModel: FollowingViewModel
    private var _binding: FollowingFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FollowingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FollowingViewModel::class.java)
        binding.rvFollowing.setHasFixedSize(true)
        binding.rvFollowing.layoutManager = LinearLayoutManager(this.context)
        val username = arguments?.getString(EXTRA_USERNAME)
        viewModel.setListFollowing(username)
        viewModel.getListFollowing().observe(viewLifecycleOwner, { list ->
            binding.rvFollowing.adapter = CardViewAdapter(list)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}