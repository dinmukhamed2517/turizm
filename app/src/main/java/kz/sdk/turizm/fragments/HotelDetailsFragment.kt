package kz.sdk.turizm.fragments

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kz.sdk.turizm.R
import kz.sdk.turizm.base.BaseFragment
import kz.sdk.turizm.databinding.FragmentHotelDetailsBinding

class HotelDetailsFragment:BaseFragment<FragmentHotelDetailsBinding>(FragmentHotelDetailsBinding::inflate) {

    private val args:HotelDetailsFragmentArgs by navArgs()
    override fun onBindView() {
        super.onBindView()
        val hotel = args.hotel
        with(binding){

            backBtn.setOnClickListener {
                findNavController().popBackStack()

            }
            mapBtn.setOnClickListener {
                findNavController().navigate(HotelDetailsFragmentDirections.actionHotelDetailsFragmentToMapFragment(hotel))
            }
            img.setImageResource(hotel?.img!!)
            title.text = hotel.title
            rating.text = hotel.rating.toString()
            reviews.text = hotel.reviews.toString()+" отзывов"
            description.text = hotel.description
            agreementBtn.setOnClickListener {
                findNavController().navigate(
                    R.id.action_hotelDetailsFragment_to_agreementFragment
                )
            }
        }
    }
}