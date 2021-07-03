package com.satriyawicaksana888.androidsubmission1.ui.search

import SearchViewModel
import android.content.Context
import android.content.res.TypedArray
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpRequest
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.SyncHttpClient
import com.satriyawicaksana888.androidsubmission1.R
import com.satriyawicaksana888.androidsubmission1.adapter.CardViewAdapter
import com.satriyawicaksana888.androidsubmission1.data.SearchUser
import com.satriyawicaksana888.androidsubmission1.data.User
import com.satriyawicaksana888.androidsubmission1.databinding.FragmentSearchBinding
import com.satriyawicaksana888.androidsubmission1.ui.detailuser.DetailUserFragment
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var searchViewModel: SearchViewModel

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
        binding.recycleView.setHasFixedSize(true)
//        binding.etSearchUser.setOnEditorActionListener { v, actionId, event ->
//            return@setOnEditorActionListener when (actionId) {
//                EditorInfo.IME_ACTION_SEARCH -> {
//                    showRecycleCardView(view.context, ArrayList())
//                    showLoading(true)
//                    fetchGithubUser(v.text.toString(), view.context)
//                    closeSoftKeyboard()
//                    true
//                }
//                else -> false
//            }
//        }
//        binding.etSearchUser.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//                val username = s.toString().trim()
//                if (username.isNotEmpty()) {
//                    showRecycleCardView(view.context, ArrayList())
//                    showLoading(true)
//                    fetchGithubUser(username, view.context)
//                } else {
//                    showRecycleCardView(view.context, ArrayList())
//                }
//            }
//        })
        showRecycleCardView(view.context, ArrayList())
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        binding.etSearchUser.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    view?.let { showRecycleCardView(it.context, ArrayList()) }
                    showLoading(true)
                    searchViewModel.setUserList(v.text.toString())
                    closeSoftKeyboard()
                    true
                }
                else -> false
            }
        }
        binding.etSearchUser.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val username = s.toString().trim()
                if (username.isNotEmpty()) {
                    showLoading(true)
                    searchViewModel.setUserList(username)
                } else {
                }
            }
        })
        searchViewModel.getUserList().observe(viewLifecycleOwner, {userList ->
            if (userList != null) {
                view?.let { showRecycleCardView(it.context, userList) }
                showLoading(false)
            } else {
                showUserNotFound(true)
            }
        })
    }

    private fun closeSoftKeyboard() {
        val imm =
            view?.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    private fun fetchGithubUser(username: String, context: Context) {
        val listUser = ArrayList<SearchUser>()
        val apiKey = "token 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"
        val url = "https://api.github.com/search/users?q=${username.trim()}"
        val client = AsyncHttpClient()
        client.addHeader("Authorization", apiKey)
        client.addHeader("User-Agent", "request")
        client.addHeader("Accept", "application/vnd.github.v3+json")
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                try {
                    val result = responseBody?.let { String(it) }
                    val responseArray = JSONObject(result).getJSONArray("items")
                    if (responseArray.length() == 0) {
                        showUserNotFound(true)
                    } else {
                        showUserNotFound(false)
                        for (index in 0 until responseArray.length()) {
                            val user = responseArray.getJSONObject(index)
                            val userItem = SearchUser()
                            userItem.username = user.getString("login")
                            userItem.avatars = user.getString("avatar_url")
                            listUser.add(userItem)
                        }
                        showRecycleCardView(context, listUser)
                    }
                    showLoading(false)
                } catch (e: Exception) {
                    Log.d("Failed to parse", ": ${e.message}")
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.d("Failed to fetch", ": ${error?.message.toString()}")
            }
        })
    }

    private fun showUserNotFound(state: Boolean) {
        if (state) {
            binding.ivUserNotFound.visibility = View.VISIBLE
        } else {
            binding.ivUserNotFound.visibility = View.INVISIBLE
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.INVISIBLE
        }
    }

    private fun showRecycleCardView(context: Context, list: ArrayList<SearchUser>) {
        binding.recycleView.layoutManager = LinearLayoutManager(context)
        val cardViewUserAdapter = CardViewAdapter(list)
        Log.e("List", "showRecycleCardView: $list", )
        binding.recycleView.adapter = cardViewUserAdapter
        cardViewUserAdapter.setOnItemClickCallback(object : CardViewAdapter.OnItemClickCallback {
            override fun onItemClicked(data: SearchUser) {
                val mDetailUserFragment = DetailUserFragment()
                val mBundle = Bundle()
                mBundle.putString(DetailUserFragment.EXTRA_USER, data.username)
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