package ir.opensourceapps.android.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Owner(
        val id: Long,
        @field:SerializedName("avatar_url")
        val avatarUrl: String,
        val url: String,
        val type: String
) : Parcelable