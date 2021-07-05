package com.satriyawicaksana888.androidsubmission1.ui.followers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpResponseHandler
import com.satriyawicaksana888.androidsubmission1.data.SearchUser
import com.satriyawicaksana888.androidsubmission1.utility.GithubAccess
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import java.lang.Exception

class FollowersViewModel : ViewModel() {
    companion object {
        val TAG = FollowersViewModel::class.java.simpleName
    }
    val listFollowerLiveData = MutableLiveData<ArrayList<SearchUser>>()

    fun setListFolllowers(username: String){
        val client = GithubAccess.createClient()
        client.get(GithubAccess.urlFollowerUser(username), object: AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                try {
                    val result = JSONArray(responseBody)
                    Log.e(TAG, "onSuccess: $result", )
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

    fun getListFollowers(): LiveData<ArrayList<SearchUser>> {
        return listFollowerLiveData
    }
}