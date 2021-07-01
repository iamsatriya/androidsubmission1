package com.satriyawicaksana888.androidsubmission1

import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.satriyawicaksana888.androidsubmission1.adapter.CardViewAdapter
import com.satriyawicaksana888.androidsubmission1.data.User
import com.satriyawicaksana888.androidsubmission1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_home, R.id.navigation_search, R.id.navigation_profile
        ).build()
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

//        binding.rvGithubUser.setHasFixedSize(true)
//        list.addAll(getListUser())
//        showRecyclerCardView()
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
//    private fun showRecyclerCardView() {
//        binding.rvGithubUser.layoutManager = LinearLayoutManager(this)
//        val cardViewUserAdapter = CardViewAdapter(list, this)
//        binding.rvGithubUser.adapter = cardViewUserAdapter
//    }
}