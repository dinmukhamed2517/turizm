package kz.sdk.turizm.fragments


import android.util.Log
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kz.sdk.turizm.R
import kz.sdk.turizm.adapters.CartAdapter
import kz.sdk.turizm.base.BaseFragment
import kz.sdk.turizm.databinding.FragmentCartBinding
import kz.sdk.turizm.firebase.UserDao
import kz.sdk.turizm.models.Product
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment: BaseFragment<FragmentCartBinding>(FragmentCartBinding::inflate) {
    var products: MutableList<Product> = mutableListOf()

    @Inject
    lateinit var firebaseAuth: FirebaseAuth
    @Inject
    lateinit var userDao: UserDao
    override fun onBindView() {
        userDao.getData()
        super.onBindView()

        val adapter = CartAdapter()

        adapter.itemClick = {
            findNavController().navigate(
                CartFragmentDirections.actionCartFragmentToProductDetailsFragment(it)
            )
        }
        with(binding){
            startBtn.setOnClickListener {
                findNavController().navigate(R.id.action_cartFragment_to_homeFragment)
            }

            cartRecycler.adapter = adapter
            cartRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)



            backBtn.setOnClickListener {
                findNavController().popBackStack()
            }
            adapter.deleteButtonClicked = { product ->
                val keyToDelete = userDao.getDataLiveData.value?.cart?.filterValues { it.id == product.id }?.keys?.firstOrNull()
                keyToDelete?.let { key ->
                    userDao.deleteProductFromList(key)
                    val updatedProducts = ArrayList(products).apply {
                        remove(product)
                    }
                    adapter.submitList(updatedProducts)
                    products = updatedProducts
                } ?: run {
                    Log.e("CartFragment", "Failed to find key for product deletion")
                }
            }
        }
        userDao.getDataLiveData.observe(viewLifecycleOwner) { userData ->
            products.clear()
            userData?.cart?.values?.let { productList ->
                products.addAll(productList)
            }
            adapter.submitList(products.toList())

            val isCartEmpty = products.isEmpty()
            binding.emptyCartCv.isVisible = isCartEmpty
            binding.cartRecycler.isVisible = !isCartEmpty
        }
    }
    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser == null) {
            findNavController().navigate(R.id.action_cartFragment_to_loginFragment)
        }

    }

}