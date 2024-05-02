package kz.sdk.turizm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import kz.sdk.turizm.base.BaseCountryViewHolder
import kz.sdk.turizm.databinding.ItemCountryBinding
import kz.sdk.turizm.models.Country

class CountryAdapter:ListAdapter<Country, BaseCountryViewHolder<*>>(CountryDiffUtils()) {

    var itemClick:((Country) ->Unit)? = null

    class CountryDiffUtils:DiffUtil.ItemCallback<Country>(){
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseCountryViewHolder<*> {
        return CountryViewHolder(
            ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseCountryViewHolder<*>, position: Int) {
        holder.bindView(getItem(position))
    }
    inner class CountryViewHolder(binding:ItemCountryBinding):BaseCountryViewHolder<ItemCountryBinding>(binding) {
        override fun bindView(item: Country) {
            with(binding){
                title.text = item.title
                img.setImageResource(item.img)
            }
            itemView.setOnClickListener {
                itemClick?.invoke(item)
            }
        }
    }

}