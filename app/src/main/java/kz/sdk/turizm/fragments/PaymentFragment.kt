package kz.sdk.turizm.fragments

import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import androidx.navigation.fragment.findNavController
import kz.sdk.turizm.R
import kz.sdk.turizm.base.BaseFragment
import kz.sdk.turizm.databinding.FragmentPaymentBinding

class PaymentFragment:BaseFragment<FragmentPaymentBinding>(FragmentPaymentBinding::inflate) {
    override fun onBindView() {
        super.onBindView()
        setupInputFields()
        binding.confirmPayment.setOnClickListener {
            showCustomDialog("Успех", "Вы успешно оплатили!")
            findNavController().navigate(R.id.action_paymentFragment_to_homeFragment)
        }
    }

    private fun setupInputFields() {
        binding.etCardNumber.filters = arrayOf(InputFilter.LengthFilter(16))
        binding.etCvc.filters = arrayOf(InputFilter.LengthFilter(3))
        binding.etDate.filters = arrayOf(InputFilter.LengthFilter(5))
    }
}