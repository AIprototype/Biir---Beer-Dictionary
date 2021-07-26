package com.example.biir.data.network.models.response

import com.google.gson.annotations.SerializedName

data class BeerListDetailResponseModel(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("tagline") val tagline: String?,
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("abv") val abv: String?,
    @SerializedName("ibu") val ibu: String?,
    @SerializedName("brewers_tips") val brewers_tips: String?,
    @SerializedName("contributed_by") val contributed_by: String?,
) {

}