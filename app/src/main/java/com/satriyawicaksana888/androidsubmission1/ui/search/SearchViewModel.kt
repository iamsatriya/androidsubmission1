import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.satriyawicaksana888.androidsubmission1.data.SearchUser
import com.satriyawicaksana888.androidsubmission1.utility.GithubAccess
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class SearchViewModel : ViewModel() {
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
}