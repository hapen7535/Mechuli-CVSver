package com.example.mechulicvs.View

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.example.mechulicvs.Model.Recipeinfo
import com.example.mechulicvs.R

class RecipeListAdapter(
    private val context: Context,
    val itemList : List<Recipeinfo>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<RecipeListAdapter.ViewHolder>(){

    private lateinit var itemClickListener: AdapterView.OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(v: Recipeinfo, pos: Int)
    }


    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        private val postThumb = itemView.findViewById<ImageView>(R.id.post_thumbnail_iv)
        private val postName = itemView.findViewById<TextView>(R.id.post_name_tv)
        private val postDate = itemView.findViewById<TextView>(R.id.post_date_tv)
        private val postWriter = itemView.findViewById<TextView>(R.id.post_nickname_tv)
        private val commentCnt = itemView.findViewById<TextView>(R.id.comment_count_tv)
        private val ratingAvg = itemView.findViewById<TextView>(R.id.rating_count_tv)

        fun bind(datas : Recipeinfo, context : Context){
            if(datas.recipeImgTitle != ""){
                postThumb.load(datas.recipeImgTitle){
                    placeholder(R.drawable.recipe_thumbnail_placeholder)
                    scale(Scale.FILL)
                }
            }
            postName.text = datas.recipeTitle
            postDate.text = datas.updateTime
            postWriter.text = datas.userNickName
            commentCnt.text = datas.recipeReplyCount.toString()
            ratingAvg.text = datas.recipeAverageScore.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.community_postlist_rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: RecipeListAdapter.ViewHolder, position: Int) {
        val model = itemList.get(position)

        holder.bind(itemList[position], context)
        holder.itemView.setOnClickListener {
            listener.onItemClick(model, position)
        }
    }


}