package io.github.aimsio.tablerecyclerview

import androidx.recyclerview.widget.DiffUtil

class TableModelDiffCallback(
    private val oldList: List<TableModel>,
    private val newList: List<TableModel>,
    private val tableRowView: TableRowView
) : DiffUtil.Callback() {

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldList[oldItemPosition]
        val new = newList[newItemPosition]

        return tableRowView.areContentsTheSame(old = old, new = new)
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldList[oldItemPosition]
        val new = newList[newItemPosition]

        return tableRowView.areItemsTheSame(old = old, new = new)
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val old = oldList[oldItemPosition]
        val new = newList[newItemPosition]

        return tableRowView.getChangePayload(old = old, new = new)
    }
}