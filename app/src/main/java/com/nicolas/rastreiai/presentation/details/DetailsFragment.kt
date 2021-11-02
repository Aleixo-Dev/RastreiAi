package com.nicolas.rastreiai.presentation.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.nicolas.rastreiai.R
import com.nicolas.rastreiai.common.AdapterDetails
import com.nicolas.rastreiai.common.showToast
import com.nicolas.rastreiai.databinding.DetailsFragmentBinding
import com.nicolas.rastreiai.domain.model.OrderStateUiDomain
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val arguments: DetailsFragmentArgs by navArgs()

    private val viewModel: DetailsViewModel by viewModels()

    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val details = arguments.orderEntity
        binding.apply {
            tvNameOrder.text = details.title
            showToast(details.code)
        }
        getDetailsOrder(details.code)
    }

    private fun getDetailsOrder(code: String) = binding.apply {
        viewModel.run {
            postCode(code)
            responseCode.observe(viewLifecycleOwner) { stateOrder ->
                setupRecyclerViewDetails(stateOrder)
            }
        }
    }

    private fun setupRecyclerViewDetails(stateOrder: List<OrderStateUiDomain>) = binding.apply {
        with(recyclerDetails) {
            adapter = AdapterDetails(stateOrder)
            setHasFixedSize(true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}