package kz.sdk.turizm.models

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize


@Parcelize
data class Country(
    var id:Int,
    var title:String,
    @DrawableRes
    var img:Int,
    var products:List<Product>? = null,
):Parcelable
