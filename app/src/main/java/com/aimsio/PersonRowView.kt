package com.aimsio

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import io.github.aimsio.tablerecyclerview.TableModel
import io.github.aimsio.tablerecyclerview.TableRowView
import kotlinx.android.synthetic.main.table_row.view.*

class PersonRowView : TableRowView {
    override fun createView(inflater: LayoutInflater, parent: ViewGroup): View {
        return inflater.inflate(R.layout.table_row, parent, false)
    }

    override fun bindView(
        rowIndex: Int,
        hiddenColumnsIndices: List<Int>,
        model: TableModel,
        rowView: View,
        payLoad: MutableList<Any>?
    ) {

        model as Person

        rowView.apply {
            txtId.text = model.id
            txtFirstName.text = model.firstName
            txtLastName.text = model.lastName
            txtEmail.text = model.email
            txtGender.text = model.gender
            txtCity.text = model.city
            txtCountry.text = model.country

            Picasso.get().load(model.avatar).into(imgAvatar)

            group.visibility = View.VISIBLE

            hiddenColumnsIndices.forEach {
                when (it) {
                    0 -> txtId.visibility = View.GONE
                    1 -> imgAvatar.visibility = View.GONE
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

    override fun areContentsTheSame(old: TableModel, new: TableModel): Boolean {
        old as Person
        new as Person
        return old == new
    }

    override fun areItemsTheSame(old: TableModel, new: TableModel): Boolean {
        old as Person
        new as Person
        return old.id == new.id
    }

    override fun getChangePayload(old: TableModel, new: TableModel): List<Any>? {
        return emptyList()
    }

}