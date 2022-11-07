package com.example.mechulicvs.View

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.mechulicvs.DetailAddRatingActivity
import com.example.mechulicvs.Model.MenuList
import com.example.mechulicvs.R
import com.example.mechulicvs.databinding.ActivityAddRatingBinding

class AddRatingAdapter(
    private val context: Context, val itemList : List<MenuList>
) : RecyclerView.Adapter<AddRatingAdapter.ViewHolder>(){

    private lateinit var itemClickListener : OnItemClickListener

    interface OnItemClickListener {
        fun onClick(view : View, pos: Int)
    }

    fun setOnItemClickListener( onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    var datas = listOf<MenuList>()

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        private val itemImg = itemView.findViewById<ImageView>(R.id.item_image_iv)
        private val itemName = itemView.findViewById<TextView>(R.id.item_name_tv)
        private val storeName = itemView.findViewById<TextView>(R.id.item_brand_name_tv)

        fun bind(datas : MenuList, context : Context){
            if(datas.menu_image != ""){
                itemImg.load(datas.menu_image){
                    transformations(CircleCropTransformation())
                }
            } else{
                itemImg?.setImageResource(R.mipmap.ic_launcher) //사진 데이터 없을 시 안드로이드 기본 사진
            }
            itemName.text = datas.menu_name
            storeName.text = datas.store_name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddRatingAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recommend_list_recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position], context)
    }

}