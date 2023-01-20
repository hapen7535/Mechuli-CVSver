package com.example.mechulicvs.ui.community.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mechulicvs.R

class RecipeIngrAdapter(
    private val context: Context,
    private val ingrList: MutableList<String>,
) : RecyclerView.Adapter<RecipeIngrAdapter.ViewHolder>()
{

    private var mListener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener?) {
        mListener = listener
    }
    interface OnItemClickListener {
        fun onDeleteClick(positon: Int)
    }

    private val differCallback = object : DiffUtil.ItemCallback<String>() {

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

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
        private val deleteBtn = view.findViewById<ImageView>(R.id.ingr_delete_btn)

        fun bind(datas: String, context: Context, position: Int) {
            ingrName.text = datas
            deleteBtn.setOnClickListener {
                removeItem(position)
            }
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(ingrList[position], context, position)
    }

    override fun getItemCount(): Int = ingrList.size

    private fun removeItem(position: Int){
        ingrList.removeAt(position)
        notifyItemRemoved(position)
    }

}