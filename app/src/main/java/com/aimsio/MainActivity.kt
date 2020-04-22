package com.aimsio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val personList = DataGenerator.personList(this)

        val columnView = PersonColumnNameView()
        val rowView = PersonRowView()

        table_recyclerview.setUp(columnView, rowView)

        table_recyclerview.updateTable(personList)
    }
}
