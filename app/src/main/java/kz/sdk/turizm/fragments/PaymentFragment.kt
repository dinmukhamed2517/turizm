package kz.sdk.turizm.fragments

import androidx.navigation.fragment.findNavController
import kz.sdk.turizm.R
import kz.sdk.turizm.base.BaseFragment
import kz.sdk.turizm.databinding.FragmentPaymentBinding

class PaymentFragment:BaseFragment<FragmentPaymentBinding>(FragmentPaymentBinding::inflate) {
    override fun onBindView() {
        super.onBindView()

        binding.confirmPayment.setOnClickListener {
            showCustomDialog("Успех", "Вы успешно оплатили!")
            findNavController().navigate(R.id.action_paymentFragment_to_homeFragment)
        }
    }


}