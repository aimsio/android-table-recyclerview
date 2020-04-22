package com.aimsio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val personList: MutableList<Person> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showTable()

        btn_add_rows.setOnClickListener {
            val id = Random(System.currentTimeMillis()).nextInt(101, 400).toString()
            personList.add(0,
                Person(
                    id = id,
                    firstName = "user $id",
                    lastName = "user $id",
                    email = "user@user$id.com",
                    gender = "LGBTQ+",
                    city = "Calgary",
                    country = "Canada",
                    avatar = "https://robohash.org/etenimvel.png?size=50x50&set=set1"
                )
            )

            table_recyclerview.updateTable(personList)
        }

        btn_remove_rows.setOnClickListener {
            personList.removeAt(0)
            table_recyclerview.updateTable(personList)
        }

        btn_show_title.setOnClickListener {
            table_recyclerview.showAllColumns()
        }

        btn_hide_title.setOnClickListener {
            table_recyclerview.hideColumns(listOf(1, 2))
        }
    }

    private fun showTable() {
        personList.addAll(DataGenerator.personList(this))

        val columnView = PersonColumnNameView()
        val rowView = PersonRowView()

        table_recyclerview.setUp(columnView, rowView)

        table_recyclerview.updateTable(personList)
    }
}
