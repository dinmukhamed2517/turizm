package kz.sdk.turizm.fragments

import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kz.sdk.turizm.R
import kz.sdk.turizm.adapters.HotelAdapter
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
        val adapter = HotelAdapter()

        with(binding){
            hotelRecycler.adapter = adapter
            hotelRecycler.layoutManager = LinearLayoutManager(requireContext())
            adapter.submitList(product.hotels)
            backBtn.setOnClickListener {
                findNavController().popBackStack()
            }
        }
        adapter.itemClick = {
            findNavController().navigate(ProductDetailsFragmentDirections.actionProductDetailsFragmentToHotelDetailsFragment(it))
        }

    }

    override fun onStart() {
        super.onStart()
    }
}