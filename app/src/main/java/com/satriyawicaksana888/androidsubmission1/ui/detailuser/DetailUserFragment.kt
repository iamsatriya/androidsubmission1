package com.satriyawicaksana888.androidsubmission1.ui.detailuser

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.satriyawicaksana888.androidsubmission1.R
import com.satriyawicaksana888.androidsubmission1.data.User
import com.satriyawicaksana888.androidsubmission1.databinding.FragmentDetailUserBinding

class DetailUserFragment : Fragment() {
    private var _binding: FragmentDetailUserBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username = arguments?.getString(EXTRA_USER)
        Log.e("user", "onViewCreated: $username", )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}