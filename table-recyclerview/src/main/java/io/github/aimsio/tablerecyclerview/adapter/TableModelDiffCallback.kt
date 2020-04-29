package io.github.aimsio.tablerecyclerview.adapter

import androidx.recyclerview.widget.DiffUtil
import io.github.aimsio.tablerecyclerview.model.TableModel
import io.github.aimsio.tablerecyclerview.model.TableRowView

/**
 * The base diffutil callback that's used to calculate and dispatch updates to TableRecyclerView
 * Nothing fancy here, just remember to cast [TableModel] objects to your own model object when
 * implementing this class
 */
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