package kz.sdk.turizm.fragments

import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kz.sdk.turizm.R
import kz.sdk.turizm.adapters.ProductAdapter
import kz.sdk.turizm.base.BaseFragment
import kz.sdk.turizm.databinding.FragmentSearchBinding
import kz.sdk.turizm.models.Product

class SearchFragment:BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {
    override fun onBindView() {
        super.onBindView()
        val adapter = ProductAdapter()
        binding.recycler.adapter =adapter

        adapter.itemClick = {
            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToProductDetailsFragment(it)
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

    fun searchProduct(input:String):List<Product>{
        val products = getList()
        return products.filter { product ->
            product.title!!.contains(input, ignoreCase = true)
        }
    }

    fun getList(): List<Product> {
        return listOf(
            Product(1, "В Египет на праздники!", R.drawable.product_1   , 250000.0, "Классная погода, теплое море и выгодные предложения!"),
            Product(2, "Малайзия, Лангкави - на майские праздники !", R.drawable.product_2, 440000.0, "Лангкави , главный остров одноимённого архипелага — самый популярный среди туристов курорт Малайзии. Лангкави славится своими пляжами с мягким белым песком и чистым морем!"),
            Product(3, "Лучшие отели Бодрума!", R.drawable.product_3, 365000.0, "Бодрум невероятно красивый курорт, с чистейшим Эгейским морем и великолепными отелями!"),
            Product(4, "Чарынский каньон. Долина замков!", R.drawable.product_4, 70000.0, "Классическая однодневная туристическая поездка в Чарынчкий каньон в \"Долину замков\". Этот тур выходного дня чрезвычайно популярен у казахстанцев и гостей страны. В прогулке по каньону Вы не останетесь равнодушны к этому чуду природы, виды как сверху в каньон, так и снизу из каньона, очень завораживают. \"Долина замков\" является самой зрелещной и самой посещаемой частью Чарынского каньона."),
            Product(5, "Алтын-Эмель: Поющий бархан, горы Актау", R.drawable.product_5   , 200000.0, "Эта двухдневная туристическая поездка стала классическим туром-экскурсией в парк Алтын-Эмель, потому что два дня - это оптимальное время необходимое для посещения основных красивейших  достопримечательностей парка: \"Поющего бархана\" и гор Актау. В этом автотуре, как ни в каком другом можно встретить даже целые стада диких животных джейранов и куланов. Прогулки по невысоким горам Актау принесёт массу незабываемых впечатлений и так же много замечательных фотографий необычных красочных природных пейзажей. А оставшимся на ночевку и отдых в Алтын-Эмеле у подножья гор Актау достаются в подарок небо из миллиардов звёзд и оглушающая тишина. Самым благоприятным временем для этого тура являются месяцы: апрель-июнь, август-октябрь."),
            Product(6, "Кольсайские озера", R.drawable.product_6, 100000.0, "Кольсайские озёра расположены на территории национального парка «Көлсай көлдері» и являются туристическим объектом. Рядом имеются гостевые дома и кемпинги. В советское время действовал туристический маршрут протяжённостью 25 км, в который входило посещение всех трёх Кольсайских озёр, а также перевала Сары-Булак и спуск к озеру Иссык-Куль. ")
        )
    }

}