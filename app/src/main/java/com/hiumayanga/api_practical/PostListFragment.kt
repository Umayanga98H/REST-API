package com.hiumayanga.api_practical

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hiumayanga.api_practical.API.RetrofitApiService
import com.hiumayanga.api_practical.adapters.PostAdapter
import com.hiumayanga.api_practical.databinding.FragmentPostListBinding
import com.hiumayanga.api_practical.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PostListFragment : Fragment() {
    companion object{
        private val TAG =PostListFragment::class.java.simpleName
    }

    private var _binding: FragmentPostListBinding?=null
    private val binding get()=_binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentPostListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerviewPost =binding.recyclerviewPosts

        adapter = PostAdapter(posts)
        recyclerviewPost.adapter=adapter

        fetchPosts()
    }

    private fun fetchPosts(){
        val call= RetrofitApiService.retrofitService.getPosts()
        call.enqueue(object: Callback<List<Post>> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                    Log.d(TAG,"Total posts: " + response.body()!!.size)

                    val postsFromResponse = response.body()
                    if(postsFromResponse != null){
                        posts.addAll(postsFromResponse)
                        adapter!!.notifyDataSetChanged()
                    }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.e(TAG,"Got error : " + t.localizedMessage)
            }


        })
    }

}