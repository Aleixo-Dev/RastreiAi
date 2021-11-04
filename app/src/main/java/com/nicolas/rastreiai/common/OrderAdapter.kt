package com.nicolas.rastreiai.common

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicolas.rastreiai.databinding.ItemsProductBinding
import com.nicolas.rastreiai.domain.model.OrderEntity

class OrderAdapter(
    private val orderStateUiList: List<OrderEntity>,
    private val clickCode: ((order: OrderEntity) -> Unit),
) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    private lateinit var itemRemoveListener: ItemRemoveListener

    interface ItemRemoveListener {
        fun onItemRemoveClicked(orderEntity: OrderEntity, position: Int)
    }

    fun setListener(itemRemoveListener: ItemRemoveListener) {
        this.itemRemoveListener = itemRemoveListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemsProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(view, clickCode)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(orderStateUiList[position], position)
    }

    override fun getItemCount() = orderStateUiList.size

    inner class ViewHolder(
        binding: ItemsProductBinding,
        private val clickCode: ((order: OrderEntity) -> Unit)
    ) : RecyclerView.ViewHolder(binding.root) {

        private val titleOrder = binding.tvProductTitle
        private val imgRemove = binding.imgRemove

        @SuppressLint("NotifyDataSetChanged")
        fun bind(orderEntity: OrderEntity, position: Int) {
            titleOrder.text = orderEntity.title

            itemView.setOnClickListener {
                clickCode.invoke(orderEntity)
            }

            imgRemove.setOnClickListener {
                itemRemoveListener.onItemRemoveClicked(orderEntity, position)
                val item = orderStateUiList[position]
                (orderStateUiList as MutableList).remove(item)
                notifyDataSetChanged()
            }
        }
    }
}