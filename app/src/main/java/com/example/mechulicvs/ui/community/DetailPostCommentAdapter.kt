package com.example.mechulicvs.ui.community

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mechulicvs.data.remote.model.Reply
import com.example.mechulicvs.R

class DetailPostCommentAdapter(
    private val context: Context, val commentList: List<Reply>
) : RecyclerView.Adapter<DetailPostCommentAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Reply>(){
        override fun areItemsTheSame(oldItem: Reply, newItem: Reply): Boolean {
            return oldItem.replyId == newItem.replyId
        }

        override fun areContentsTheSame(oldItem: Reply, newItem: Reply): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback) //AsyncListDiffer을 통해 자동으로 백그라운드에서 실행되도록 함
    //리스트가 많으면 백그라운드에서 실행하는 것이 효율적

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(commentList[position], context)
//        val comment = differ.currentList[position]
//        holder.bind(comment, context)
    }

    override fun getItemCount(): Int = commentList.size

    fun getRVItemList() : List<Reply> = commentList


}