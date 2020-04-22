package io.github.aimsio.tablerecyclerview

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

    private lateinit var rowAdapter: TableRowAdapter
    private lateinit var columnNameAdapter: TableColumnNameAdapter

    init {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.table_recyclerview, this)
        headerRecyclerview = view.findViewById(R.id.table_header_recyclerview)
        rowsRecyclerview = view.findViewById(R.id.table_rows_recyclerview)

        headerRecyclerview.layoutManager = LinearLayoutManager(context)
        rowsRecyclerview.layoutManager = LinearLayoutManager(context)
    }

    fun setUp(tableColumnNameView: TableColumnNameView, tableRowView: TableRowView) {
        columnNameAdapter = TableColumnNameAdapter(tableColumnNameView)
        headerRecyclerview.adapter = columnNameAdapter

        rowAdapter = TableRowAdapter(tableRowView)
        rowsRecyclerview.adapter = rowAdapter
    }

    fun updateTable(rows: List<TableModel>) {
        rowAdapter.updateData(rows)
    }

    fun hideColumns(hiddenColumnsIndices: List<Int>) {
        columnNameAdapter.hideColumns(hiddenColumnsIndices)
        rowAdapter.hideColumns(hiddenColumnsIndices)
    }

    fun showAllColumns() {
        columnNameAdapter.showAllColumns()
        rowAdapter.showAllColumns()
    }
}