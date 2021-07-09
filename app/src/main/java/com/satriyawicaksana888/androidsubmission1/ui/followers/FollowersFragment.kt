package com.satriyawicaksana888.androidsubmission1.ui.followers

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.satriyawicaksana888.androidsubmission1.adapter.CardViewAdapter
import com.satriyawicaksana888.androidsubmission1.databinding.FollowersFragmentBinding

class FollowersFragment : Fragment() {

    companion object {
        const val EXTRA_USERNAME = "extra_username_followers"
    }

    private lateinit var viewModel: FollowersViewModel
    private var _binding: FollowersFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FollowersFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FollowersViewModel::class.java)
        binding.rvFollowers.setHasFixedSize(true)
        binding.rvFollowers.layoutManager = LinearLayoutManager(this.context)
        val username = arguments?.getString(EXTRA_USERNAME)
        viewModel.setListFolllowers(username)
        viewModel.getListFollowers().observe(viewLifecycleOwner, { list ->
            binding.rvFollowers.adapter = CardViewAdapter(list)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}