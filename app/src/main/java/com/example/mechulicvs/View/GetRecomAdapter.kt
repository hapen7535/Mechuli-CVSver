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
import com.example.mechulicvs.Model.ItemData
import com.example.mechulicvs.R

class GetRecomAdapter(val context: Context, val itemList : List<ItemData>) : RecyclerView.Adapter<GetRecomAdapter.ViewHolder>(){

    var datas = mutableListOf<ItemData>()

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val itemImg = itemView?.findViewById<ImageView>(R.id.item_image_iv)
        val itemName = itemView?.findViewById<TextView>(R.id.item_name_tv)
        val storeName = itemView?.findViewById<TextView>(R.id.item_brand_name_tv)

        fun bind(datas : ItemData, context : Context){
            if(datas.img != ""){
//              val resourceId = context.resources.getIdentifier(datas.img, "drawable", context.packageName)
//              itemImg?.setImageResource(resourceId)
                itemImg.load(datas.img){
                    transformations(CircleCropTransformation())
                }
            } else{
                itemImg?.setImageResource(R.mipmap.ic_launcher) //사진 데이터 없을 시 안드로이드 기본 사진
            }
            itemName?.text = datas.itemName
            storeName?.text = datas.storeName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetRecomAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recommend_list_recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: GetRecomAdapter.ViewHolder, position: Int) {
        holder.bind(datas[position], context)
    }


}