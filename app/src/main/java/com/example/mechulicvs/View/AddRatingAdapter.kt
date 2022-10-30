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
    private val itemList: List<MenuList>,
    val binding: ActivityAddRatingBinding
) : RecyclerView.Adapter<AddRatingAdapter.ViewHolder>(), Filterable {

    private lateinit var itemClickListener : OnItemClickListener

    interface OnItemClickListener {
        fun onClick(view : View, pos: Int)
    }

    fun setOnItemClickListener( onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }


    var datas = listOf<MenuList>()
    var datasFilterd = listOf<MenuList>()

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        private val context = binding.root.context

        val itemImg = itemView?.findViewById<ImageView>(R.id.item_image_iv)
        val itemName = itemView?.findViewById<TextView>(R.id.item_name_tv)
        val storeName = itemView?.findViewById<TextView>(R.id.item_brand_name_tv)


        fun bind(datas : MenuList){
            if(datas.menu_image != ""){
//              val resourceId = context.resources.getIdentifier(datas.img, "drawable", context.packageName)
//              itemImg?.setImageResource(resourceId)
                itemImg.load(datas.menu_image){
                    transformations(CircleCropTransformation())
                }
            } else{
                itemImg?.setImageResource(R.mipmap.ic_launcher) //사진 데이터 없을 시 안드로이드 기본 사진
            }
            itemName?.text = datas.menu_name
            storeName?.text = datas.store_name

            itemView.setOnClickListener {
                val intent = Intent(context, DetailAddRatingActivity::class.java)
                intent.run { context.startActivity(this) }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recommend_list_recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(datas[position], context)
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    override fun getItemCount(): Int = datas.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                if(charString.isEmpty()) datasFilterd = datas else{
                    val filteredList = ArrayList<MenuList>()
                    datas.filter {
                        (it.menu_name.contains(constraint!!))
                    }
                        .forEach { filteredList.toMutableList().add(it)}
                    datasFilterd = filteredList
                }
                return FilterResults().apply{ values = datasFilterd }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                datasFilterd = results?.values as ArrayList<MenuList>
                notifyDataSetChanged()
            }

        }
    }
}