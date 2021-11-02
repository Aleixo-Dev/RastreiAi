package com.nicolas.rastreiai.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nicolas.rastreiai.R
import com.nicolas.rastreiai.common.OrderAdapter
import com.nicolas.rastreiai.databinding.HomeFragmentBinding
import com.nicolas.rastreiai.domain.model.OrderEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getOrdersList()
        setupListeners()
    }

    private fun getOrdersList() {
        viewModel.orders.observe(viewLifecycleOwner) { orderList ->
            setupRecyclerView(orderList)
        }
    }

    private fun setupListeners() = binding.apply {
        fab.setOnClickListener {
            navigateTo(R.id.action_homeFragment_to_customDialogFragment)
        }
    }

    private fun setupRecyclerView(orderList: List<OrderEntity>) = binding.apply {
        with(recyclerProducts) {
            adapter = OrderAdapter(orderList) {
                val directions = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it)
                findNavController().run {
                    navigate(directions)
                }
            }
            setHasFixedSize(true)
        }
    }

    private fun navigateTo(directions: Int) {
        findNavController().run {
            navigate(directions)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}