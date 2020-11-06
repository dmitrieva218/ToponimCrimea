package com.toponim.crimea.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.toponim.crimea.R
import com.toponim.crimea.dataclass.Word
import kotlinx.android.synthetic.main.word_item.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initView()
    }

    private fun initView() {
        var word = intent.getParcelableExtra<Word>("WORD")
        if (word != null) {
            tvWord.text = word.word
            tvDescription.text = word.description
        }
    }
}