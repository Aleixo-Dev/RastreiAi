package com.nicolas.rastreiai.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicolas.rastreiai.R
import com.nicolas.rastreiai.databinding.ItemsDetailsBinding
import com.nicolas.rastreiai.domain.model.OrderStateUiDomain

class AdapterDetails(private val listOrderState: List<OrderStateUiDomain>) :
    RecyclerView.Adapter<AdapterDetails.ViewHolder>() {

    class ViewHolder(binding: ItemsDetailsBinding) : RecyclerView.ViewHolder(binding.root) {

        private val stateDescription = binding.tvState
        private val date = binding.tvDate
        val view: View = itemView.findViewById(R.id.vertical_view)

        fun bind(orderStateUiDomain: OrderStateUiDomain) {
            stateDescription.text = orderStateUiDomain.description
            date.text = orderStateUiDomain.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemsDetailsBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent,
                false
            )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listOrderState[position])
        if (position == (itemCount) - 1) {
            holder.view.visibility = View.GONE
        }
    }

    override fun getItemCount() = listOrderState.size
}