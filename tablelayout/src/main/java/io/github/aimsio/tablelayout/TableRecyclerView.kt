package io.github.aimsio.tablelayout

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.HorizontalScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TableRecyclerView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : HorizontalScrollView(context, attributeSet) {

    private val headerRecyclerview: RecyclerView
    private val rowsRecyclerview: RecyclerView

    private lateinit var rowsAdapter: TableRowsAdapter
    private lateinit var headerAdapter: TableHeaderAdapter

    init {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.table_recyclerview, this)
        headerRecyclerview = view.findViewById(R.id.table_header_recyclerview)
        rowsRecyclerview = view.findViewById(R.id.table_rows_recyclerview)

        headerRecyclerview.layoutManager = LinearLayoutManager(context)
        rowsRecyclerview.layoutManager = LinearLayoutManager(context)
    }

    fun setUp(tableHeaderView: TableHeaderView, tableRowView: TableRowView) {
        headerAdapter = TableHeaderAdapter(tableHeaderView)
        headerRecyclerview.adapter = headerAdapter

        rowsAdapter = TableRowsAdapter(tableRowView)
        rowsRecyclerview.adapter = rowsAdapter
    }

    fun updateTable(rows: List<TableModel>) {
        rowsAdapter.updateData(rows)
    }

    fun hideColumns(hiddenColumnsIndices: List<Int>) {
        headerAdapter.hideColumns(hiddenColumnsIndices)
        rowsAdapter.hideColumns(hiddenColumnsIndices)
    }

    fun showAllColumns() {
        headerAdapter.showAllColumns()
        rowsAdapter.showAllColumns()
    }
}