package com.satriyawicaksana888.androidsubmission1.utility

import com.loopj.android.http.AsyncHttpClient

class GithubAccess {
    companion object {
        const val apiKey = "token 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"
        fun urlUserList(q: String) : String{
            return "https://api.github.com/search/users?q=${q.trim()}"
        }
        fun urlDetailUser(username: String): String{
            return "https://api.github.com/users/${username.trim()}"
        }
        fun urlFollowerUser(username: String):String{
            return "https://api.github.com/users/{${username.trim()}}/followers"
        }
        fun urlFollowingUser(username: String):String{
            return "https://api.github.com/users/{${username.trim()}}/following"
        }
        fun createClient(): AsyncHttpClient {
            val client = AsyncHttpClient()
            client.addHeader("Authorization", apiKey)
            client.addHeader("User-Agent", "request")
            client.addHeader("Accept", "application/vnd.github.v3+json")
            return client
        }
    }
}