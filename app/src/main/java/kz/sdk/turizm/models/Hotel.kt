package kz.sdk.turizm.models

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.google.android.gms.maps.model.LatLng
import kotlinx.parcelize.Parcelize


@Parcelize
data class Hotel(
    var id:Int? = null,
    var title:String? = null,
    @DrawableRes
    var img:Int? = null,
    var location:String? = null,
    var date:String? = null,
    var period:String? = null,
    var rating:Double? = null,
    var reviews:Int? = null,
    var price:Double? = null,
    var description:String? = null,
    var lat: Double? = null,
    var lng: Double? = null
):Parcelable