package kz.sdk.turizm.fragments

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kz.sdk.turizm.R
import kz.sdk.turizm.adapters.ProductAdapter
import kz.sdk.turizm.base.BaseFragment
import kz.sdk.turizm.databinding.FragmentHomeBinding
import kz.sdk.turizm.models.Product

@AndroidEntryPoint

class HomeFragment:BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    override fun onBindView() {
        super.onBindView()

        var adapter1 = ProductAdapter()
        var adapter2 = ProductAdapter()
        binding.searchBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }
        binding.recycler1.adapter = adapter1
        binding.recycler1.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.recycler2.adapter = adapter2
        binding.recycler2.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        adapter1.submitList(getList())
        adapter2.submitList(getList2())


        adapter1.itemClick = {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToProductDetailsFragment(
                    it
                )
            )
        }
        adapter2.itemClick = {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToProductDetailsFragment(
                    it
                )
            )
        }
    }

    fun getList(): List<Product> {
        return listOf(
            Product(1, "В Египет на праздники!", R.drawable.product_1   , 250000.0, "Классная погода, теплое море и выгодные предложения!"),
            Product(2, "Малайзия, Лангкави - на майские праздники !", R.drawable.product_2, 440000.0, "Лангкави , главный остров одноимённого архипелага — самый популярный среди туристов курорт Малайзии. Лангкави славится своими пляжами с мягким белым песком и чистым морем!"),
            Product(3, "Лучшие отели Бодрума!", R.drawable.product_3, 365000.0, "Бодрум невероятно красивый курорт, с чистейшим Эгейским морем и великолепными отелями!"),
        )
    }

    fun getList2():List<Product>{
        return listOf(
            Product(4, "Чарынский каньон. Долина замков!", R.drawable.product_4, 70000.0, "Классическая однодневная туристическая поездка в Чарынчкий каньон в \"Долину замков\". Этот тур выходного дня чрезвычайно популярен у казахстанцев и гостей страны. В прогулке по каньону Вы не останетесь равнодушны к этому чуду природы, виды как сверху в каньон, так и снизу из каньона, очень завораживают. \"Долина замков\" является самой зрелещной и самой посещаемой частью Чарынского каньона."),
            Product(5, "Алтын-Эмель: Поющий бархан, горы Актау", R.drawable.product_5   , 200000.0, "Эта двухдневная туристическая поездка стала классическим туром-экскурсией в парк Алтын-Эмель, потому что два дня - это оптимальное время необходимое для посещения основных красивейших  достопримечательностей парка: \"Поющего бархана\" и гор Актау. В этом автотуре, как ни в каком другом можно встретить даже целые стада диких животных джейранов и куланов. Прогулки по невысоким горам Актау принесёт массу незабываемых впечатлений и так же много замечательных фотографий необычных красочных природных пейзажей. А оставшимся на ночевку и отдых в Алтын-Эмеле у подножья гор Актау достаются в подарок небо из миллиардов звёзд и оглушающая тишина. Самым благоприятным временем для этого тура являются месяцы: апрель-июнь, август-октябрь."),
            Product(6, "Кольсайские озера", R.drawable.product_6, 100000.0, "Кольсайские озёра расположены на территории национального парка «Көлсай көлдері» и являются туристическим объектом. Рядом имеются гостевые дома и кемпинги. В советское время действовал туристический маршрут протяжённостью 25 км, в который входило посещение всех трёх Кольсайских озёр, а также перевала Сары-Булак и спуск к озеру Иссык-Куль. ")
        )
    }
}