package io.github.aimsio.tablerecyclerview.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.aimsio.tablerecyclerview.model.TableModel

/**
 * The interface for how a table row is built and created
 */
interface TableRowView {

    fun createView(inflater: LayoutInflater, parent: ViewGroup): View

    fun bindView(rowIndex: Int, hiddenColumnsIndices: List<Int>, model: TableModel, rowView: View, payLoad: MutableList<Any>? = null)

    fun areContentsTheSame(old: TableModel, new: TableModel): Boolean

    fun areItemsTheSame(old: TableModel, new: TableModel): Boolean

    fun getChangePayload(old: TableModel, new: TableModel): List<Any>?
}

