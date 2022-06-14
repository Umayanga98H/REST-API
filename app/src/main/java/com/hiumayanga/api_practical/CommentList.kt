package com.hiumayanga.api_practical

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hiumayanga.api_practical.API.RetrofitApiService
import com.hiumayanga.api_practical.PostDetails.Companion.POST_ID
import com.hiumayanga.api_practical.adapters.CommentAdapter
import com.hiumayanga.api_practical.databinding.FragmentCommentListBinding
import com.hiumayanga.api_practical.model.Comment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentList : Fragment() {
    companion object{
        private val TAG=CommentList::class.java.simpleName
    }
    private var _binding:FragmentCommentListBinding? = null
    private val binding get()  = _binding!!
    private lateinit var adapter:CommentAdapter
    private val comments:MutableList<Comment> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

            override fun onCreateView(
                inflater: LayoutInflater,
                container:ViewGroup?,
                savedInstanceState:Bundle?
            ):View? {
                _binding = FragmentCommentsListBinding.inflate(inflater,container,false)

                return binding.root
            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerViewPostComments = binding.recyclerviewPostComments

        adapter = CommentAdapter(comments)
        recyclerViewPostComments.adapter=adapter

        fetchComments()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun fetchComments(){
        val postId = arguments?.getInt(POST_ID)
        postId?.let{ RetrofitApiService.retrofitService.getCommentsList(it)}
            ?.enqueue(object : Callback<List<Comment>> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(
                    call: Call<List<Comment>>,
                    response: Response<List<Comment>>
                ){
                    val commentsFromResponse =response.body()
                    if (commentsFromResponse != null){
                        comments.addAll(commentsFromResponse)
                        adapter.notifyDataSetChaged()
                    }
                }

                override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                    Log.e(TAG,"Got error : " + t.localizedMessage)
                }
            })
    }
}