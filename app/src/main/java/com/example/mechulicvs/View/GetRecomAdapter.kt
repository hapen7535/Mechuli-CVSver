package com.example.mechulicvs.View

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.mechulicvs.model.MenuList
import com.example.mechulicvs.R

class GetRecomAdapter(private val context: Context, val itemList : List<MenuList>) : RecyclerView.Adapter<GetRecomAdapter.ViewHolder>(){

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetRecomAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recommend_list_recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: GetRecomAdapter.ViewHolder, position: Int) {
        holder.bind(itemList[position], context)
    }


}