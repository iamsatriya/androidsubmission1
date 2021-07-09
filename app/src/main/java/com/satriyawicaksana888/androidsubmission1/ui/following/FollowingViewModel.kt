package com.satriyawicaksana888.androidsubmission1.ui.following

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpResponseHandler
import com.satriyawicaksana888.androidsubmission1.data.SearchUser
import com.satriyawicaksana888.androidsubmission1.ui.followers.FollowersViewModel
import com.satriyawicaksana888.androidsubmission1.utility.GithubAccess
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import java.lang.Exception

class FollowingViewModel : ViewModel() {
    companion object {
        val TAG = FollowingViewModel::class.java.simpleName
    }
    val listFollowing = MutableLiveData<ArrayList<SearchUser>>()
    fun setListFollowing(username: String?) {
        val list = ArrayList<SearchUser>()
        val client = GithubAccess.createClient()
        client.get(
            username?.let { GithubAccess.urlFollowingUser(it) },
            object : AsyncHttpResponseHandler() {
                override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseBody: ByteArray?
                ) {
                    try {
                        val result = responseBody?.let { String(it) }
                        val resultArray = JSONArray(result)
                        for (i in 0 until resultArray.length()) {
                            val user = SearchUser()
                            user.username = resultArray.getJSONObject(i).getString("login")
                            user.avatars = resultArray.getJSONObject(i).getString("avatar_url")
                            list.add(user)
                        }
                        listFollowing.postValue(list)
                    } catch (e: Exception) {
                        Log.d(TAG, "onFailedFetch: ${e.message}")
                    }
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseBody: ByteArray?,
                    error: Throwable?
                ) {
                    Log.d(TAG, "onFailure: ${error?.message}")
                }

            })
    }
    fun getListFollowing(): LiveData<ArrayList<SearchUser>> {
        return listFollowing
    }
}