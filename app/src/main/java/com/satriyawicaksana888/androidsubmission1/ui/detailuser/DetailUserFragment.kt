package com.satriyawicaksana888.androidsubmission1.ui.detailuser

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.satriyawicaksana888.androidsubmission1.R

class DetailUserFragment : Fragment() {

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    private lateinit var viewModel: DetailUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_user_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailUserViewModel::class.java)
        // TODO: Use the ViewModel
        val username = arguments?.getString(EXTRA_USER)
        Log.e("user", "onViewCreated: $username")
    }

}