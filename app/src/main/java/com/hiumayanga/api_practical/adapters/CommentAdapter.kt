package com.hiumayanga.api_practical.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hiumayanga.api_practical.R
import com.hiumayanga.api_practical.model.Comment

class CommentAdapter (
    private val comments:List<Comment>
        ): RecyclerView.Adapter<CommentAdapter.CommentViewHolder>(){
            class CommentViewHolder(private val view: View):RecyclerView.ViewHolder(view){
                var textViewCommentName: TextView =view.findViewById(R.id.textview_comment_name)
                var textViewCommentBody: TextView=view.findViewById(R.id.textview_comment_name)
                var textViewEmail: TextView=view.findViewById(R.id.textview_email)
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val layout=LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_comment_item,parent,false)
        return CommentViewHolder(layout)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment=comments[position]

        holder.textViewCommentName.text= comment.name
        holder.textViewCommentBody.text= comment.body
        holder.textViewEmail.text= comment.email
    }

    override fun getItemCount()=comments.size


}