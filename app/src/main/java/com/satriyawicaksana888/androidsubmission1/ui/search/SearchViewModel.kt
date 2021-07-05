import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.satriyawicaksana888.androidsubmission1.ProfileActivity
import com.satriyawicaksana888.androidsubmission1.data.SearchUser
import com.satriyawicaksana888.androidsubmission1.data.User
import com.satriyawicaksana888.androidsubmission1.ui.search.SearchFragment
import com.satriyawicaksana888.androidsubmission1.utility.GithubAccess
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class SearchViewModel : ViewModel() {
    companion object {
        val TAG = SearchViewModel::class.java.simpleName
    }
    val listUsersLiveData = MutableLiveData<ArrayList<SearchUser>>()

    fun setUserList(username: String) {
        val listUser = ArrayList<SearchUser>()
        val client = GithubAccess.createClient()
        client.get(GithubAccess.urlUserList(username), object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                try {
                    val result = responseBody?.let { String(it) }
                    val responseArray = JSONObject(result).getJSONArray("items")
                    for (index in 0 until responseArray.length()) {
                        val user = responseArray.getJSONObject(index)
                        val userItem = SearchUser()
                        userItem.username = user.getString("login")
                        userItem.avatars = user.getString("avatar_url")
                        listUser.add(userItem)
                    }
                    listUsersLiveData.postValue(listUser)
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

    fun getUserList(): LiveData<ArrayList<SearchUser>> {
        return listUsersLiveData
    }

    fun openDetailUser(username: String?, context: Context) {
        val client = GithubAccess.createClient()
        client.get(username?.let { GithubAccess.urlDetailUser(it) }, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                try {
                    val result = JSONObject(responseBody?.let { String(it) })
                    val name = result.getString("name")
                    val company = result.getString("company")
                    val location = result.getString("location")
                    val followers = result.getInt("followers").toString()
                    val following = result.getInt("following").toString()
                    val repository = result.getInt("public_repos").toString()
                    val avatarUrl = result.getString("avatar_url")
                    val htmlUrl = result.getString("html_url")
                    val githubUser = User(
                        username,
                        name,
                        location,
                        repository,
                        company,
                        followers,
                        following,
                        avatarUrl,
                        htmlUrl
                    )
                    val mIntent = Intent(context, ProfileActivity::class.java)
                    mIntent.putExtra(ProfileActivity.EXTRA_USER, githubUser)
                    context.startActivity(mIntent)
                } catch (e: Exception) {
                    Log.d(TAG, "onFailedToFetch: ${e.message}")
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
}