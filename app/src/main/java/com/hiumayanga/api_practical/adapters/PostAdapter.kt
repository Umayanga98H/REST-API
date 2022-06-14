package com.hiumayanga.api_practical.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.hiumayanga.api_practical.R
import com.hiumayanga.api_practical.model.Post

class PostAdapter(
    private val posts: List<Post>
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    class PostViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val textviewPostId: TextView = view.findViewById(R.id.textview_post_id)
        val textviewPostTitle: TextView = view.findViewById(R.id.textview_post_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_post_item, parent, false)

        return PostViewHolder(layout)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]

        holder.textviewPostId.text=post.id.toString()
        holder.textviewPostTitle.text=post.title

        holder.view.setOnClickListener{
            val action=PostsListFragmentDirections
                .actionPostListFragmentToPostDetailsFragment(
                    postId=holder.textviewPostId.text.toString().toInt()
                )
            it.findNavController().navigate(action)
        }

    }

    override fun getItemCount() = posts.size
}


