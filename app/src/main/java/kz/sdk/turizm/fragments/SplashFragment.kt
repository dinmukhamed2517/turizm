import android.content.Context
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.*
import kz.sdk.turizm.R
import kz.sdk.turizm.base.BaseFragment
import kz.sdk.turizm.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    override var showBottomNavigation: Boolean = false

    private val coroutineScope = CoroutineScope(Dispatchers.Main + Job())

    override fun onBindView() {
        super.onBindView()
        coroutineScope.launch {
            delay(3000)
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel() 
    }
}
