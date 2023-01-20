package com.example.mechulicvs.ui.community.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mechulicvs.R
import com.example.mechulicvs.data.remote.model.Reply

class RecipeIngrAdapter(
    private val context: Context,
    val ingrList: List<String>,
) : RecyclerView.Adapter<RecipeIngrAdapter.ViewHolder>()
{

    private val differCallback = object : DiffUtil.ItemCallback<String>() {

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeIngrAdapter.ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.community_ingr_item, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val ingrName = view.findViewById<TextView>(R.id.ingr_item_name)

        fun bind(datas: String, context: Context) {
            ingrName.text = datas
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(ingrList[position], context)
    }

    override fun getItemCount(): Int = ingrList.size
}