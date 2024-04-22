package kz.sdk.turizm.fragments

import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kz.sdk.turizm.R
import kz.sdk.turizm.base.BaseFragment
import kz.sdk.turizm.databinding.FragmentProductDetailsBinding
import kz.sdk.turizm.firebase.UserDao
import javax.inject.Inject

@AndroidEntryPoint
class ProductDetailsFragment: BaseFragment<FragmentProductDetailsBinding>(FragmentProductDetailsBinding::inflate) {
    private val args:ProductDetailsFragmentArgs by navArgs()
    @Inject
    lateinit var userDao: UserDao
    @Inject
    lateinit var firebaseAuth: FirebaseAuth
    override fun onBindView() {
        super.onBindView()
        val product = args.product

        with(binding){
            backBtn.setOnClickListener {
                findNavController().popBackStack()
            }
            if(firebaseAuth.currentUser == null){
                favorite.isVisible = false
            }

            title.text = product.title
            product.img?.let { imageView3.setImageResource(it) }
            price.text = "От "+product.price.toString()+" KZT"
            description.text = product.description

            favorite.setOnClickListener {
                userDao.saveProductToList(product)
                showCustomDialog("Успешно" , "Добавлено в избранное")
            }
            addBtn.setOnClickListener {
                findNavController().navigate(R.id.action_productDetailsFragment_to_agreementFragment)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if(firebaseAuth.currentUser == null){
            binding.favorite.isVisible = false
        }
    }
}