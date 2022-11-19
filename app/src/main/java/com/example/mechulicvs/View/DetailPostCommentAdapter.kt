package com.example.mechulicvs.View

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mechulicvs.Model.Reply
import com.example.mechulicvs.R

class DetailPostCommentAdapter(
private val context: Context, val imgList: List<Reply>
) : RecyclerView.Adapter<DetailPostCommentAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailPostCommentAdapter.ViewHolder {
        TODO("Not yet implemented")
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val replyHolder : TextView = view.findViewById(R.id.comments_list_rv)
    }

    override fun onBindViewHolder(holder: DetailPostCommentAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


}