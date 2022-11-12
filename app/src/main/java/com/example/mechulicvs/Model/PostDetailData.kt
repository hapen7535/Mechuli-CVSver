package com.example.mechulicvs.Model

import com.google.gson.annotations.SerializedName

data class PostDetailData (
    val isSuccess : Boolean,
    val message : String,
    val result : PostDetail,
        )

data class PostDetail (
    @SerializedName("recipe_id") val recipeId : Int,
    @SerializedName("user_id") val userId : String,
    @SerializedName("user_nickname") val userNickName : String,
    @SerializedName("recipe_reply_count") val replyCount : Int,
    @SerializedName("recipe_average_score") val AvgScore : Double,

    @SerializedName("recipe_title") val recipeTitle : String,
    @SerializedName("recipe_ingredient") val recipeIngr : String,
    @SerializedName("recipe_cost") val recipeCost : Int,
    @SerializedName("recipe_content") val recipeCont : String,

    @SerializedName("create_time") val createTime : String,
    @SerializedName("update_time") val updateTime : String,

    @SerializedName("recipe_img_url_1") val recipeImg1 : String,
    @SerializedName("recipe_img_url_2") val recipeImg2 : String,
    @SerializedName("recipe_img_url_3") val recipeImg3 : String,
    @SerializedName("recipe_img_url_4") val recipeImg4 : String,
    @SerializedName("recipe_img_url_5") val recipeImg5 : String,

    @SerializedName("recipe_replyList") val replayList : Reply,
        )

data class Reply(

    @SerializedName("reply_id") val replyId : Int,
    @SerializedName("reply_user_id") val replyUserId : String,
    @SerializedName("reply_nickname") val replyNickName : String,
    @SerializedName("reply_content") val replyContent : String,
    @SerializedName("reply_score") val replyScore : Double,
    @SerializedName("reply_create_time") val replyCreateTime : String,

)