package kz.sdk.turizm.fragments

import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kz.sdk.turizm.R
import kz.sdk.turizm.base.BaseFragment
import kz.sdk.turizm.databinding.FragmentMapBinding

class MapFragment: BaseFragment<FragmentMapBinding>(FragmentMapBinding::inflate), OnMapReadyCallback {
    private val args: MapFragmentArgs by navArgs()
    private lateinit var mMap: GoogleMap
    override var showBottomNavigation = false

    override fun onBindView() {
        super.onBindView()
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.zoomIn.setOnClickListener {
            mMap.animateCamera(CameraUpdateFactory.zoomIn())
        }

        binding.zoomOut.setOnClickListener {
            mMap.animateCamera(CameraUpdateFactory.zoomOut())
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val zoomLevel = 17.0f
        val latLng = LatLng(args.hotel.lat!!, args.hotel.lng!!)
        mMap.addMarker(MarkerOptions().position(latLng!!).title(args.hotel.title))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel))

    }

}
