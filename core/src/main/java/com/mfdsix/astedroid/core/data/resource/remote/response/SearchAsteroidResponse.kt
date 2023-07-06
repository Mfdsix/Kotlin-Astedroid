package com.mfdsix.astedroid.core.data.resource.remote.response

import com.google.gson.annotations.SerializedName

data class SearchAsteroidResponse(
    @field:SerializedName("collection")
    val collection: SearchAsteroidItems
)

data class SearchAsteroidItems(
    @field:SerializedName("items")
    val items: List<SearchAsteroidItem>
)

data class SearchAsteroidItem(
    @field:SerializedName("data")
    val data: List<SearchAsteroidItemData>,

    @field:SerializedName("links")
    val links: List<SearchAsteroidItemLink>?
)

data class SearchAsteroidItemData(
    @field:SerializedName("nasa_id")
    val nasaId: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("center")
    val center: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("date_created")
    val dateCreated: String
)

data class SearchAsteroidItemLink(
    @field:SerializedName("href")
    val href: String
)