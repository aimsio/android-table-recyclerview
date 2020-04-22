package io.github.aimsio.tablelayout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

interface TableModel

interface TableRowView {

    fun createView(inflater: LayoutInflater, parent: ViewGroup): View

    fun bindView(rowIndex: Int, hiddenColumnsIndices: List<Int>, model: TableModel, rowView: View, payLoad: MutableList<Any>? = null)

    fun areContentsTheSame(old: TableModel, new: TableModel): Boolean

    fun areItemsTheSame(old: TableModel, new: TableModel): Boolean

    fun getChangePayload(old: TableModel, new: TableModel): List<Any>?
}

interface TableHeaderView {

    fun createView(inflater: LayoutInflater, parent: ViewGroup): View

    fun bindView(columnView: View, hiddenColumnsIndices: List<Int>)
}