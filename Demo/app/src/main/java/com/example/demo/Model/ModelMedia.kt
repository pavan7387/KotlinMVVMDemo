package com.example.demo.Model

import com.google.gson.annotations.SerializedName

class ModelMedia {

    @SerializedName("format")
    var format: String? = null
    @SerializedName("width")
    var width: Int? = null
    @SerializedName("height")
    var height: Int? = null
    @SerializedName("filename")
    var filename: String? = null
    @SerializedName("id")
    var id: Int? = null
    @SerializedName("author")
    var author: String? = null
    @SerializedName("author_url")
    var authorUrl: String? = null
    @SerializedName("post_url")
    var postUrl: String? = null
}