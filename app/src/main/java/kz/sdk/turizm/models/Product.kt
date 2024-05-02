package kz.sdk.turizm.models

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    var id:Int? = null,
    var title:String? = null,
    @DrawableRes
    var img:Int? = null,
    var price:Double?= null,
    var description:String? = null,
    var hotels:List<Hotel>? = null,
): Parcelable