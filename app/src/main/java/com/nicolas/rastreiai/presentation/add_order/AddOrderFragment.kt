package com.nicolas.rastreiai.presentation.add_order

import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.nicolas.rastreiai.databinding.NewCustomDialogBinding
import androidx.fragment.app.viewModels
import com.nicolas.rastreiai.common.Constants
import com.nicolas.rastreiai.common.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddOrderFragment : DialogFragment() {

    private val viewModel: AddOrderViewModel by viewModels()

    private var _binding: NewCustomDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NewCustomDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        configInputCode()
    }

    private fun setupListeners() = binding.apply {
        buttonCancel.setOnClickListener {
            backStack()
        }
        buttonConfirm.setOnClickListener {
            if (inputName.text.isNullOrEmpty().not()) {
                if (inputCode.text.isNullOrEmpty().not()) {
                    checkCodeOrder(inputCode.text.toString())
                } else {
                    inputCode.error = "inform code"
                }
            } else {
                inputName.error = "inform name"
            }
        }
    }

    private fun configInputCode() = binding.apply {
        inputCode.filters = arrayOf(
            LengthFilter(13),
            InputFilter.AllCaps()
        )
    }

    private fun addOrderInLocalDatabase(title: String, code: String) {
        viewModel.insertOrderToDatabase(title, code)
    }

    private fun checkCodeOrder(code: String) = binding.apply {
        viewModel.run {
            postCodeOrder(code)
            orderState.observe(viewLifecycleOwner) { stateOrder ->
                when (stateOrder) {
                    is AddOrderViewModel.OrderUiState.Loading -> {

                    }
                    is AddOrderViewModel.OrderUiState.Success -> {
                        stateOrder.orderList.let {
                            if (it.isEmpty()) {
                                showToast("Objeto não encontrado.")
                            } else {
                                addOrderInLocalDatabase(
                                    inputName.text.toString(),
                                    stateOrder.orderList[0].code.toString()
                                )
                                backStack()
                            }
                        }
                    }
                    is AddOrderViewModel.OrderUiState.Error -> {
                        showToast(stateOrder.error)
                    }
                }
            }
        }
    }

    private fun backStack() {
        findNavController().run {
            popBackStack()
        }
    }
}