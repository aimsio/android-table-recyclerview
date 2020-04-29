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

        personList.addAll(DataGenerator.personList(this))

        /**
         * 1- prepare your implementation of ColumnName view
         * 2- prepare your implementation of Row View
         * notice that in order to columns widths to stay consistent,
         * you better use the same view for row and column name layout.
         * the heights of rows are controlled by [TableRecyclerView]
         */
        val columnView = PersonColumnNameView()
        val rowView = PersonRowView()

        /**
         * 3- Call setUp, passing row and columnName view implementations
         */
        table_recyclerview.setUp(columnView, rowView)

        /**
         * 4- Call updateTable and pass your list of data
         */
        table_recyclerview.updateTable(personList)
    }

    override fun onResume() {
        super.onResume()

        setButtonClickListeners()
    }

    private fun setButtonClickListeners() {
        btn_add_rows.setOnClickListener {
            val id = Random(System.currentTimeMillis()).nextInt(101, 400).toString()
            personList.add(
                0,
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

        btn_show_all_columns.setOnClickListener {
            table_recyclerview.showAllColumns()
        }

        /**
         * Columns are zero-indexed, so this methods hide the second and third column!
         */
        btn_hide_columns.setOnClickListener {
            table_recyclerview.hideColumns(listOf(1, 2))
        }
    }
}
