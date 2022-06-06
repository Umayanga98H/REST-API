package com.hiumayanga.api_practical

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.hiumayanga.api_practical.API.RetrofitApiService
import com.hiumayanga.api_practical.adapters.PostAdapter
import com.hiumayanga.api_practical.database.post.Post
import com.hiumayanga.api_practical.databinding.FragmentPostListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PostListFragment : Fragment() {

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
 //       binding.buttonTemp.setOnClickListener{
 //           fetchPosts()
 //       }
        fetchPosts()
    }

    private fun fetchPosts(){
        val call= RetrofitApiService.retrofitService.getPosts()
        call.enqueue(object: Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                //Toast.makeText(context,response.body()?.size.toString(),Toast.LENGTH_SHORT).show()
                val posts= response.body()
                val adapter=posts?.let { PostAdapter(it) }
                binding.recyclerviewPosts.adapter=adapter
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.d("PostsListFragment",t.message.toString())
            }


        })
    }

}