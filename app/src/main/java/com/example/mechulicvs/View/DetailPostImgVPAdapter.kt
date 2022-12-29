package com.example.mechulicvs.View

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mechulicvs.R

class DetailPostImgVPAdapter(
    private val context: Context, val imgList: List<String>
) : RecyclerView.Adapter<DetailPostImgVPAdapter.ViewHolder>(){


    //    override fun getCount(): Int = imgList.size
//
//    override fun isViewFromObject(view: View, `object`: Any): Boolean {
//        return view === `object`
//    }
//
//    override fun instantiateItem(container: ViewGroup, position: Int): Any {
//        val view : View = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
//            R.layout.post_img_vp_item, null)
//        val ivImages = view.findViewById<ImageView>(R.id.detail_post_iv)
//
//        imgList[position].let {
//            ivImages.load(it)
//        }
//
//        val vp = container as ViewPager2
//        vp.addView(view, 0)
//        return view
//
//    }
//
//    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        val vp = container as ViewPager2
//        val view = `object` as View
//        vp.removeView(view)
//    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val imagesHolder : ImageView = view.findViewById(R.id.detail_post_iv)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailPostImgVPAdapter.ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.post_img_vp_item, parent, false)
        view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailPostImgVPAdapter.ViewHolder, position: Int) {
        holder.imagesHolder.load(imgList[position])
    }

    override fun getItemCount(): Int = imgList.size

}