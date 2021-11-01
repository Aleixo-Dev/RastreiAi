package com.nicolas.rastreiai.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicolas.rastreiai.databinding.ItemsProductBinding
import com.nicolas.rastreiai.domain.model.OrderEntity

class OrderAdapter(
    private val orderStateUiList: List<OrderEntity>
) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemsProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(orderStateUiList[position])
    }

    override fun getItemCount() = orderStateUiList.size

    inner class ViewHolder(binding: ItemsProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val titleOrder = binding.tvProductTitle

        fun bind(orderEntity: OrderEntity) {
            titleOrder.text = orderEntity.title
        }
    }
}