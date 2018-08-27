package ir.opensourceapps.android.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Content(
        val name: String,
        val path: String,
        @field:SerializedName("html_url")
        val htmlUrl: String,
        val content: String,
        val encoding: String
) : Parcelable