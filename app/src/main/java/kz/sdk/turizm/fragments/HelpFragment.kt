package kz.sdk.turizm.fragments

import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kz.sdk.turizm.R
import kz.sdk.turizm.base.BaseFragment
import kz.sdk.turizm.databinding.FragmentHelpBinding
import javax.inject.Inject


@AndroidEntryPoint
class HelpFragment:BaseFragment<FragmentHelpBinding>(FragmentHelpBinding::inflate) {
    @Inject
    lateinit var firebaseAuth: FirebaseAuth
    override fun onBindView() {
        super.onBindView()


        with(binding){
            backBtn.setOnClickListener {
                findNavController().popBackStack()

            }

            confirmButton.setOnClickListener {
                if(etLetter.text.toString().isEmpty()){
                    showCustomCancelDialog("Ошибка", "Пожалуйста заполните письмо")
                    tilLetter.isErrorEnabled = true
                    tilLetter.error = "Заполните"
                }
                else{
                    tilLetter.isErrorEnabled = false
                    showCustomDialog("Успех", "Ваше письмо отправлено в министерство. Спасибо за помощь!")
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser == null) {
            findNavController().navigate(R.id.action_helpFragment_to_loginFragment)
        }

    }

}