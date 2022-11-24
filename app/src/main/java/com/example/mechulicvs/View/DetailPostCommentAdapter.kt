package com.example.mechulicvs.View

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mechulicvs.Model.MenuList
import com.example.mechulicvs.Model.Reply
import com.example.mechulicvs.R

class DetailPostCommentAdapter(
    private val context: Context, val commentList: List<Reply>
) : RecyclerView.Adapter<DetailPostCommentAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailPostCommentAdapter.ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.comment_recylcerview_item, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val commentNickName = view.findViewById<TextView>(R.id.nick_name_tv)
        private val commentContents = view.findViewById<TextView>(R.id.comment_contents_tv)
        private val commentRatings = view.findViewById<RatingBar>(R.id.comment_rating_rb)
        private val commentDate = view.findViewById<TextView>(R.id.comment_date_tv)

        fun bind(datas: Reply, context: Context) {
            commentContents.text = datas.replyContent
            commentNickName.text = datas.replyNickName
            commentDate.text = datas.replyCreateTime
            commentRatings.rating = datas.replyScore.toFloat()
        }

    }

    override fun onBindViewHolder(holder: DetailPostCommentAdapter.ViewHolder, position: Int) {
        holder.bind(commentList[position], context)
    }

    override fun getItemCount(): Int = commentList.size


}