package com.aimsio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.aimsio.tablerecyclerview.TableColumnNameView
import kotlinx.android.synthetic.main.table_column.view.*

class PersonColumnNameView : TableColumnNameView {
    override fun createView(inflater: LayoutInflater, parent: ViewGroup): View {
        return inflater.inflate(R.layout.table_column, parent, false)
    }

    override fun bindView(columnView: View, hiddenColumnsIndices: List<Int>) {
        val resources = columnView.context.resources
        columnView.apply {
            txtId.text = resources.getString(R.string.id)
            txtAvatar.text = resources.getString(R.string.avatar)
            txtFirstName.text = resources.getString(R.string.first_name)
            txtLastName.text = resources.getString(R.string.last_name)
            txtEmail.text = resources.getString(R.string.email)
            txtGender.text = resources.getString(R.string.gender)
            txtCity.text = resources.getString(R.string.city)
            txtCountry.text = resources.getString(R.string.country)

            group.visibility = View.VISIBLE

            hiddenColumnsIndices.forEach {
                when (it) {
                    0 -> txtId.visibility = View.GONE
                    1 -> txtAvatar.visibility = View.GONE
                    2 -> txtFirstName.visibility = View.GONE
                    3 -> txtLastName.visibility = View.GONE
                    4 -> txtEmail.visibility = View.GONE
                    5 -> txtGender.visibility = View.GONE
                    6 -> txtCity.visibility = View.GONE
                    7 -> txtCountry.visibility = View.GONE
                }
            }
        }
    }
}