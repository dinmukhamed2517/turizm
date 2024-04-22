package kz.sdk.turizm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import kz.sdk.turizm.base.BaseProductViewHolder
import kz.sdk.turizm.databinding.ItemProductBinding
import kz.sdk.turizm.models.Product

class ProductAdapter:ListAdapter<Product, BaseProductViewHolder<*>>(ProductDiffUtils()) {

    var itemClick:((Product) ->Unit)? = null


    class ProductDiffUtils:DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseProductViewHolder<*> {
        return ProductViewHolder(
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseProductViewHolder<*>, position: Int) {
        holder.bindView(getItem(position))
    }

    inner class ProductViewHolder(binding:ItemProductBinding):BaseProductViewHolder<ItemProductBinding>(binding) {
        override fun bindView(item: Product) {
            with(binding){
                title.text = item.title
                item.img?.let { imageView.setImageResource(it) }
                price.text = "От "+item.price.toString()+" KZT"

            }
            itemView.setOnClickListener {
                itemClick?.invoke(item)
            }
        }
    }
}