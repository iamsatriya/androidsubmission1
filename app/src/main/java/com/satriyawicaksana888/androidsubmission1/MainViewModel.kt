package com.satriyawicaksana888.androidsubmission1

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.satriyawicaksana888.androidsubmission1.data.User
import cz.msebera.android.httpclient.Header

class MainViewModel : ViewModel() {
    val listUsers = MutableLiveData<List<User>>()
    fun getListUser(): LiveData<List<User>> {
        return listUsers
    }

    fun setListUser(username: String) {
        val listUser = ArrayList<User>()
        val apiKey = "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"
        val url = "https://api.github.com/search/users?q=$username"
        val client = AsyncHttpClient()
        client.addHeader("Authorization", "token $apiKey")
        client.addHeader("Accept", "application/vnd.github.v3+json")
        client.get(url, object: AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                Log.e("Hasil", "onSuccess: ${responseBody?.let { String(it) }}", )
                Log.e("JSON", "onSuccess: $responseBody", )
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.e("Failed", "onFailure: ", )
            }
        })
    }
}