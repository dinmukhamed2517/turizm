package kz.sdk.turizm.fragments

import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.model.LatLng
import kz.sdk.turizm.R
import kz.sdk.turizm.adapters.CountryAdapter
import kz.sdk.turizm.adapters.ProductAdapter
import kz.sdk.turizm.base.BaseFragment
import kz.sdk.turizm.databinding.FragmentSearchBinding
import kz.sdk.turizm.models.Country
import kz.sdk.turizm.models.Hotel
import kz.sdk.turizm.models.Product

class SearchFragment:BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {
    override fun onBindView() {
        super.onBindView()
        val adapter = CountryAdapter()
        binding.recycler.adapter =adapter

        adapter.itemClick = {
            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToProductsFragment(it)
            )
        }
        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.recycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.editText.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH){
                if(binding.editText.text!!.isNotEmpty()){
                    adapter.submitList(searchProduct(binding.editText.text.toString()))
                }
                else{
                    Toast.makeText(requireContext(), "Введите название товара", Toast.LENGTH_SHORT).show()
                }
            }
            false
        })
    }

    fun searchProduct(input:String):List<Country>{
        val products = getCountryList()
        return products.filter { product ->
            product.title!!.contains(input, ignoreCase = true)
        }
    }

    fun getCountryList():List<Country>{
        return listOf(
            Country(1, "Турция", R.drawable.s1, listOf(
                Product(1, "Великолепный Стамбул! Почувствуйте себя Султаном Сулейманом и Хюррем!", R.drawable.p1, 290000.0, "Стамбул, это город с невероятной историей, огромным количеством достопримечательностей и просто одень душевный город!\n" +
                        "Бронируйте туры с прямым перелетом и почувствуй всю прелесть этого города", listOf(
                    Hotel(1, "MEGARON HOTEL", R.drawable.h1, "Турция, Стамбул", "28 Мая", "7-8 ночей", 6.7, 435, 621000.0, "Hotel Megaron — это отель в центре города Стамбул, расположенный в менее чем 1 км и 800 м соответственно от таких достопримечательностей, как Голубая мечеть и Цистерна Базилика. В распоряжении гостей отеля с 3 звездами — общий лаундж, а также номера с кондиционером, бесплатным Wi-Fi и собственной ванной комнатой. В распоряжении гостей общая кухня, доставка еды и напитков и организация экскурсий.\n" +
                            "В номерах есть телевизор с плоским экраном. Из окон некоторых номеров в Hotel Megaron открывается вид на город. Гостям Hotel Megaron предоставляются постельное белье и полотенца.", 41.00581457208963, 28.972556184356208
                    ),
                    Hotel(2, "ENDICAN SULTANAHMET HOTEL", R.drawable.h2, "Турция, Стамбул", "30 Мая", "7-8 ночей", 7.8, 500, 694000.0, "Endican Sultanahmet Hotel — это отель в центре города Стамбул, расположенный в менее чем 1 км и 700 м соответственно от таких достопримечательностей, как Голубая мечеть и Цистерна Базилика. Этот вариант размещения располагается в 1,4 км и 1,4 км соответственно от таких достопримечательностей, как Дворец Топкапы и Мечеть Сулеймание. Галатская башня — в 4,9 км. Гости могут обратиться к сотрудникам круглосуточной стойки регистрации, воспользоваться трансфером от/до аэропорта или банкоматом, а также подключиться к бесплатному Wi-Fi.\n" +
                            "В номерах в Endican Sultanahmet Hotel установлен кондиционер, телевизор с плоским экраном и сейф. Среди прочих удобств — письменный стол, чайник и мини-бар, а также собственная ванная комната с биде. Гостям Endican Sultanahmet Hotel предоставляются постельное белье и полотенца.", 41.007979795050886, 28.972185507648835,
                    ),
                    Hotel(3, "THE MERETTO HOTEL ISTANBUL ", R.drawable.h3, "Турция, Стамбул", "29 Мая", "6-7 ночей", 8.8, 550, 707000.0, "Недавно построенный отель со стильными звукоизолированными номерами расположен в 2,2 км от исторического района Султанахмет. К услугам гостей крытый бассейн и турецкая баня.\n" +
                            "Постояльцам отеля The Meretto Istanbul предоставляются современные номера с кондиционером, ЖК-телевизором и бесплатным Wi-Fi. В каждом из них есть французский балкон с видом на город. В числе удобств принадлежности для чая/кофе.\n" +
                            "По утрам подают завтрак по меню. В ресторане отеля Meretto с обслуживанием по меню на обед и ужин гостям предложат блюда французской и итальянской кухни. В баре можно заказать различные напитки и закуски.\n" +
                            "В отеле есть сауна и гидромассажная ванна. Кроме того, в распоряжении гостей стиральная машина и гладильные принадлежности.\n" +
                            "Станция метро Aksaray находится в 950 метрах от отеля. Расстояние до аэропорта Стамбул составляет 45 км. За дополнительную плату для гостей организуют трансфер от/до аэропорта.", 41.010602922863086, 28.953677554933133,
                    )


                )),
                Product(2,  "Лучшие отели Бодрума! По самым вкусным ценам!", R.drawable.p2, 361000.0, "Бодрум невероятно красивый курорт, с чистейшим Эгейским морем и великолепными отелями!", ),
                Product(3, "Шикарный SIANJI WELL-BEING RESORT 5* в Бодруме!", R.drawable.p3, 885000.0, "Отель с видом на греческий остров Кос, с детокс питанием (под запрос) и термальным бассейном!"),
            )),
            Country(2, "Египет", R.drawable.s2, listOf(
                Product(4, "Новый Rixos Radamis Tirana Hotel 5* в Египте с доступом к аквапарку и ресторанам Rixos Seagate!", R.drawable.p4, 438900.0, "Недавно открывшийся семейный отель, у которого есть бесплатный доступ к самой главной фишке популярного в Египте отеля Rixоs Seagate - его аквапарку! А стоимость дешевле на 15-20%! Питание, конечно, как во всех Rixоs, и плюс - новые интересные номера в морской тематике!", listOf(
                    Hotel(4, "RIXOS SHARM EL", R.drawable.h4, "Египет, Шарм-эль-Шейх", "28 Мая", "7-8 ночей", 6.7, 435, 721000.0, "Насладитесь великолепным сервисом в Rixos Sharm El Sheikh - Ultra All Inclusive Adults Only 18 PlusКурортный отель Rixos Sharm El Sheikh расположен рядом с морем в окружении пальм. Во всех зонах предоставляется бесплатный Wi-Fi. К услугам гостей песчаный пляж, 7 бассейнов, 7 ресторанов с обслуживанием по меню и 9 баров. За дополнительную плату на частном пляже и в беседках возле бассейна можно воспользоваться услугами персонального дворецкого.\n" +
                            "Во всех номерах отеля Rixos есть балкон или терраса с видом на море, сад либо бассейн. Каждый номер оснащен телевизором с плоским экраном и телефоном, в числе удобств мини-бар и шкаф для одежды. В некоторых номерах есть просторная гостиная зона.", 28.030404137913234, 34.43720683174429
                    )
                ))
            )),
            Country(3, "Вьетнам", R.drawable.s3, ),
            Country(4, "ОАЭ", R.drawable.s4, ),
            Country(5, "Испания", R.drawable.s5, ),
            Country(6, "Таиланд", R.drawable.s6, ),
            Country(7, "Китай", R.drawable.s7, ),
            Country(7, "Мальдивы", R.drawable.s8, )

        )
    }

}