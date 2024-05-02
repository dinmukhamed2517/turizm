package kz.sdk.turizm.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import kz.sdk.turizm.models.Country
import kz.sdk.turizm.models.Hotel
import kz.sdk.turizm.models.Product

abstract class BaseViewHolder<VB : ViewBinding, T>(protected open val binding: VB) :
    RecyclerView.ViewHolder(binding.root) {
    abstract fun bindView(item: T)
}

abstract class BaseProductViewHolder<VB : ViewBinding>(override val binding: VB) :
    BaseViewHolder<VB, Product>(binding)
abstract class BaseCountryViewHolder<VB : ViewBinding>(override val binding: VB) :
    BaseViewHolder<VB, Country>(binding)

abstract class BaseHotelViewHolder<VB : ViewBinding>(override val binding: VB) :
    BaseViewHolder<VB, Hotel>(binding)