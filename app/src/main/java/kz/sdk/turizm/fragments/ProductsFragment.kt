package kz.sdk.turizm.fragments

import android.system.Os.remove
import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kz.sdk.turizm.adapters.ProductAdapter
import kz.sdk.turizm.base.BaseFragment
import kz.sdk.turizm.databinding.FragmentProductDetailsBinding
import kz.sdk.turizm.databinding.FragmentProductsBinding
import kz.sdk.turizm.firebase.UserDao
import javax.inject.Inject


@AndroidEntryPoint

class ProductsFragment:BaseFragment<FragmentProductsBinding>(FragmentProductsBinding::inflate) {

    @Inject
    lateinit var firebaseAuth: FirebaseAuth
    @Inject
    lateinit var userDao: UserDao
    private val args: ProductsFragmentArgs by navArgs()
    override fun onBindView() {
        super.onBindView()
        val country = args.country
        val adapter = ProductAdapter()

        with(binding){
            recycler.adapter = adapter
            recycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter.submitList(country.products)
            backBtn.setOnClickListener {
                findNavController().popBackStack()
            }
            countryName.text = country.title
            adapter.addButtonClicked = { product ->
                userDao.saveProductToList(product)
                showCustomDialog("Успех", "Вы сохранили тур в избранное")
            }
        }
        adapter.itemClick = {
            findNavController().navigate(ProductsFragmentDirections.actionProductsFragmentToProductDetailsFragment(it))
        }

    }

}