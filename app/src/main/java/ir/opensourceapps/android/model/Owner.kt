package ir.opensourceapps.android.model

import com.google.gson.annotations.SerializedName

data class Owner(
        val id: Long,
        @field:SerializedName("avatar_url")
        val avatarUrl: String,
        val url: String,
        val type: String
)