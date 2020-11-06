package com.toponim.crimea.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.toponim.crimea.App
import com.toponim.crimea.R
import com.toponim.crimea.dataclass.Word
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Watcher {
    private val words = App.instance.db.wordDao()
    private var dataList = listOf<Word>()
    private lateinit var wordsAdapter: WordsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addDataToDatabase()
        initView()
    }

    private fun addDataToDatabase() {
        words.addAllWord(
            listOf(
                Word(0,"АБДАРМА","- с крымско-татарского \"торопиться\", река со скалистым руслом, каскадами и небольшим водопадом в лесистой балке того же имени, ошибочно называемая Индол, под СВ склоном горы Сугут-Оба, к ЮВ от села Земляничное."),
                Word(1, "АБДУГА", "- длинный волнистый хребет, дугообразно изогнутый к В; покрыт лесом, среди которого местами встречаются поляны. По п. берегу р. Коса, ответвляясь к ЮЗ–З от г. Голый Шпиль I; в 5-6 км к Ю–ЮЗ от нас. пункта Краснолесье (Симф.)"),
                Word(2, "АГАРМЫШ", "Большой Агармыш, Агермыш, Агирмиш-Даги* - протяженная ЮЗ–СВ лесистая гора с плоской вершиной,   в зап. части которой поляны и скальные обнажения; склоны изрезаны оврагами, юж. частично обнажен. В 4 км к ЗСЗ от центра Старого Крыма; тюрк.   агармыш – седой."),
                Word(3, "АЙЯ", "Агия*-Бурун* - высокий мыс, образованный круто обрывающимися на ЮЗ скалами гг. Кокия-Кала и      Самналых-Бурун. В 9 км к ЮЮВ от Балаклавы, в 6 км к СЗ от м. Сарыч,  вблизи известны руины средневековой церкви."),
                Word(4, "БЕШТЕРЕК", "- река, начинается на сев. склонах Долгоруковской яйлы. Впадает слева в р. Зуя уже сухой балкой у нас. пункта Харитоновка (Симф.); бассейн р. Салгир I; тюрк.  беш терек – пять деревьев; иран.  дерек – река."),
                Word(5, "БУРУЛЬЧА", "Бурунча, Баурча, Бор-Ульча (Тырке в верхнем течении)  -  п. приток р. Салгир I; начинается на сев. склонах возвышенности Тырке III, в верховьях течет узкой лесистой долиной, местами по каменистому руслу с каскадами и ваннами. Впадает у нас.пункта Новоникольское (Кр. Гв.); тюрк.  бурул – “быть скрученным” и бурулма – поворот; средневековое  баур – чай-река."),
                Word(6, "ГАВРЕЛЬ", "- крутой лесистый овраг. Спускается с сев. части Бабуган-яйлы в Центральную котловину; бассейн р. Альма."),
                Word(7, "ГОЛУБАЯ БУХТА", "Делиман - самая зап. из бухт Нового Света (Судак.), между мысом Капчик и г. Караул-Оба; при ней Царский пляж. В некоторых источниках топоним Делиман перенесен на соседнюю с ней Синюю бухту."),
                Word(8, "ГОЛАЯ ГОРА", "Лысая гора - каменистая, вверху голая вершина; на ней выходы скал, разделенные небольшими седловинами; на склонах редколесье; в некоторых ракурсах предстает конусом. Ю-вост. отрог г. Перчем-Кая, в 1,2 км к З от центра Судака."),
                Word(9, "ЕРМИТАШ", "– остроконечная скала на юж. склоне г. Кошка I в Симеизе; образует “ухо кошки”."),
                Word(10, "ЗОЛОТЫЕ ВОРОТА", "Ворота Кара-Дага, Шайтан*-Капу* - скала в море в виде высокой арки. У массива Кара-Даг I, пол хребтом Хоба-Тепе."),
            )
        )
    }

    private fun initView() {
        dataList = words.getAllWords()
        wordsAdapter = WordsAdapter(this, dataList)
        rvWords.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = wordsAdapter
        }
        svWords.setOnQueryTextListener(object: SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchText(newText?.trim())
                return true
            }
        })
    }

    private fun searchText(word: String?) {
        if (word != null) {
            dataList = words.getCurrentWord(word.toUpperCase())
            wordsAdapter.setData(dataList)
        }
    }

    override fun showDetailedScreen(position: Int) {
        val intent = Intent(this, DetailActivity().javaClass)
        intent.putExtra("WORD", dataList[position])
        startActivity(intent)
    }

}