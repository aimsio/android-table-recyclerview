package io.github.aimsio.tablerecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup

class TableColumnNameAdapter(private val tableColumnNameView: TableColumnNameView) : TableViewAdapter() {

    private val hiddenColumnsIndices: MutableList<Int> = mutableListOf()

    fun hideColumns(list: List<Int>) {
        hiddenColumnsIndices.clear()
        hiddenColumnsIndices.addAll(list)

        notifyDataSetChanged()
    }

    fun showAllColumns() {
        hiddenColumnsIndices.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = tableColumnNameView.createView(inflater, parent)
        return TableViewHolder(view)
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        tableColumnNameView.bindView(
            columnView = holder.itemView,
            hiddenColumnsIndices = hiddenColumnsIndices
        )
    }
}