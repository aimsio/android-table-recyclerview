package io.github.aimsio.tablerecyclerview.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * The interface for how the table columns names are built and displayed
 */
interface TableColumnNameView {

    fun createView(inflater: LayoutInflater, parent: ViewGroup): View

    fun bindView(columnView: View, hiddenColumnsIndices: List<Int>)
}