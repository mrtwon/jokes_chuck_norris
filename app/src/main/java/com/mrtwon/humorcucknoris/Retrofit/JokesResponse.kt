package com.mrtwon.humorcucknoris.Retrofit

import com.google.gson.annotations.SerializedName
/*
* Pojo of the response from the server
 */
data class JokesResponse(

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("value")
	val value: List<JokeData>? = null
)

data class JokeData(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("categories")
	val categories: List<String?>? = null,

	@field:SerializedName("joke")
	val joke: String? = null
)
