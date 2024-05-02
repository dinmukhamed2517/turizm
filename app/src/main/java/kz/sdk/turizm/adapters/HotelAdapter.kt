package kz.sdk.turizm.adapters

import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import kz.sdk.turizm.base.BaseHotelViewHolder
import kz.sdk.turizm.databinding.ItemHotelBinding
import kz.sdk.turizm.models.Hotel

class HotelAdapter:ListAdapter<Hotel, BaseHotelViewHolder<*>>(HotelDiffUtils()) {

    var itemClick: ((Hotel) -> Unit)? = null

    class HotelDiffUtils:DiffUtil.ItemCallback<Hotel>(){
        override fun areItemsTheSame(oldItem: Hotel, newItem: Hotel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Hotel, newItem: Hotel): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHotelViewHolder<*> {
        return HotelViewHolder(
            ItemHotelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseHotelViewHolder<*>, position: Int) {
        holder.bindView(getItem(position))
    }


    inner class HotelViewHolder(binding:ItemHotelBinding):BaseHotelViewHolder<ItemHotelBinding>(binding){
        override fun bindView(item: Hotel) {
            with(binding){
                item.img?.let { img.setImageResource(it) }
                title.text = item.title
                rating.text = item.rating.toString()
                reviews.text = item.reviews.toString()+" отзывов"
                date.text = item.date
                period.text = item.period
                price.text = item.price?.toInt().toString()+" ₸"
            }
            itemView.setOnClickListener {
                itemClick?.invoke(item)
            }
        }

    }
}