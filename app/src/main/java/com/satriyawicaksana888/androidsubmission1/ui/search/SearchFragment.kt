package com.satriyawicaksana888.androidsubmission1.ui.search

import android.content.Context
import android.content.res.TypedArray
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.LinearLayoutManager
import com.satriyawicaksana888.androidsubmission1.R
import com.satriyawicaksana888.androidsubmission1.adapter.CardViewAdapter
import com.satriyawicaksana888.androidsubmission1.data.User
import com.satriyawicaksana888.androidsubmission1.databinding.FragmentSearchBinding
import com.satriyawicaksana888.androidsubmission1.ui.detailuser.DetailUserFragment

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val list = ArrayList<User>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvGithubUser.setHasFixedSize(true)
        list.addAll(getListUser())
        showRecycleCardView(view.context)
        binding.etSearchUser.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    true
                }
                else -> false
            }
        }
    }

    private fun getGithubUser(username: String): ArrayList<User> {
        val listUser = ArrayList<User>()

        return listUser
    }

    private fun getListUser(): ArrayList<User> {
        val dataUsername = resources.getStringArray(R.array.username)
        val dataName = resources.getStringArray(R.array.name)
        val dataLocation = resources.getStringArray(R.array.location)
        val dataRepository = resources.getStringArray(R.array.repository)
        val dataCompany = resources.getStringArray(R.array.company)
        val dataFollowers = resources.getStringArray(R.array.followers)
        val dataFollowing = resources.getStringArray(R.array.following)
        val dataAvatars: TypedArray = resources.obtainTypedArray(R.array.avatar)
        val listUser = ArrayList<User>()
        for (index in dataUsername.indices) {
            val user = User(
                dataUsername[index],
                dataName[index],
                dataLocation[index],
                dataRepository[index],
                dataCompany[index],
                dataFollowers[index],
                dataFollowing[index],
                dataAvatars.getResourceId(index, 0),
            )
            listUser.add(user)
        }
        dataAvatars.recycle()
        return listUser
    }

    private fun showRecycleCardView(context: Context) {
        binding.rvGithubUser.layoutManager = LinearLayoutManager(context)
        val cardViewUserAdapter = CardViewAdapter(list, context)
        binding.rvGithubUser.adapter = cardViewUserAdapter
        cardViewUserAdapter.setOnItemClickCallback(object : CardViewAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                val mDetailUserFragment = DetailUserFragment()
                val mBundle = Bundle()
                mBundle.putParcelable(DetailUserFragment.EXTRA_USER, data)
                mDetailUserFragment.arguments = mBundle
                val mFragmentManager = fragmentManager
                mFragmentManager?.beginTransaction()?.apply {
                    replace(
                        R.id.nav_host_fragment,
                        mDetailUserFragment,
                        DetailUserFragment::class.java.simpleName
                    )
                    addToBackStack(null)
                    commit()
                }
            }
        })
    }
}