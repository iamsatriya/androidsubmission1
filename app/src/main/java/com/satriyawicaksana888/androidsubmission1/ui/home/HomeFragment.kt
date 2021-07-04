package com.satriyawicaksana888.androidsubmission1.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.satriyawicaksana888.androidsubmission1.data.SearchUser
import com.satriyawicaksana888.androidsubmission1.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val list = ArrayList<SearchUser>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    /*private fun getListUser(): ArrayList<User> {
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
    }*/

    /*private fun showRecycleCardView(context: Context) {
        binding.rvGithubUser.layoutManager = LinearLayoutManager(context)
        val cardViewUserAdapter = CardViewAdapter(list, context)
        binding.rvGithubUser.adapter = cardViewUserAdapter
        cardViewUserAdapter.setOnItemClickCallback(object: CardViewAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
//                val mIntent = Intent(context, ProfileActivity::class.java)
//                mIntent.putExtra(ProfileActivity.EXTRA_USER, data)
//                context.startActivity(mIntent)
                val mDetailUserFragment = DetailUserFragment()
                val mBundle = Bundle()
                mBundle.putParcelable(DetailUserFragment.EXTRA_USER, data)
                mDetailUserFragment.arguments = mBundle
                val mFragmentManager = fragmentManager
                mFragmentManager?.beginTransaction()?.apply {
                    replace(R.id.nav_host_fragment, mDetailUserFragment, DetailUserFragment::class.java.simpleName)
                    addToBackStack(null)
                    commit()
                }
            }

        })
    }*/
}