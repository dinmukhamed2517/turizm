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

        binding.etDate.addTextChangedListener(object : TextWatcher {
            private var current = ""
            private val mmYyPattern = Regex("(\\d{1,2})(\\d{1,2})?")

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    val input = s.toString()
                    if (input != current) {
                        val match = mmYyPattern.find(input.replace("/", ""))?.groupValues

                        if (match != null) {
                            var month = match[1]
                            var year = match.getOrNull(2) ?: ""

                            if (month.length == 1 && month.toInt() > 1) {
                                month = "0$month/"
                            } else if (month.length == 2) {
                                if (month.toInt() > 12) month = "12"
                                month += "/"
                            }

                            current = "$month$year"
                            s.replace(0, s.length, current)
                        }
                    }
                }
            }
        })
    }
}