package io.github.aimsio.tablerecyclerview.adapter

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.HorizontalScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.aimsio.tablerecyclerview.R
import io.github.aimsio.tablerecyclerview.model.TableColumnNameView
import io.github.aimsio.tablerecyclerview.model.TableModel
import io.github.aimsio.tablerecyclerview.model.TableRowView

/**
 * Custom view for displaying rows of data in 2D tabular format.
 * Add this to your layout, then call [setUp].
 * for displaying and updating data in TableRecycler, call [updateTable] anytime
 */
open class TableRecyclerView @JvmOverloads constructor(
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

    /**
     * This is the first method you should call for setting up your [TableRecyclerView].
     * Pass your implementation of how table rows and column headers are built
     * Notice that you only have to invoke this method once when setting up your layout.
     */
    open fun setUp(tableColumnNameView: TableColumnNameView, tableRowView: TableRowView) {
        columnNameAdapter = TableColumnNameAdapter(tableColumnNameView)
        headerRecyclerview.adapter = columnNameAdapter

        rowAdapter = TableRowAdapter(tableRowView)

        rowAdapter.setHasStableIds(true)
        columnNameAdapter.setHasStableIds(true)

        rowsRecyclerview.adapter = rowAdapter
    }

    /**
     * Displays or updates the rows in [TableRecyclerView]
     */
    open fun updateTable(rows: List<TableModel>) {
        rowAdapter.updateData(rows)
    }

    /**
     * Hides columns specified by index, starting from zero, in [TableRecyclerView]
     */
    open fun hideColumns(hiddenColumnsIndices: List<Int>) {
        columnNameAdapter.hideColumns(hiddenColumnsIndices)
        rowAdapter.hideColumns(hiddenColumnsIndices)
    }

    /**
     * Clears the hidden columns list and displays all columns of data in [TableRecyclerView]
     */
    open fun showAllColumns() {
        columnNameAdapter.showAllColumns()
        rowAdapter.showAllColumns()
    }
}