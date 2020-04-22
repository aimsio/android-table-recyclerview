package io.github.aimsio.tablerecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil

class TableRowsAdapter(private val tableRowView: TableRowView) : TableViewAdapter() {

    private val currentList: MutableList<TableModel> = mutableListOf()

    private val hiddenColumnsIndices: MutableList<Int> = mutableListOf()

    fun updateData(newList: List<TableModel>) {
        val diffCallback = TableModelDiffCallback(
            oldList = currentList,
            newList = newList,
            tableRowView = tableRowView
        )

        val diffResult = DiffUtil.calculateDiff(diffCallback)

        currentList.clear()
        currentList.addAll(newList)

        diffResult.dispatchUpdatesTo(this)
    }

    fun hideColumns(list: List<Int>) {
        hiddenColumnsIndices.clear()
        hiddenColumnsIndices.addAll(list)

        notifyDataSetChanged()
    }

    fun showAllColumns() {
        hiddenColumnsIndices.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = tableRowView.createView(inflater, parent)
        return TableViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: TableViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        val model = currentList[holder.adapterPosition]

        tableRowView.bindView(
            rowIndex = holder.adapterPosition,
            hiddenColumnsIndices = hiddenColumnsIndices,
            model = model,
            rowView = holder.itemView,
            payLoad = payloads
        )
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        val model = currentList[holder.adapterPosition]

        tableRowView.bindView(
            rowIndex = holder.adapterPosition,
            hiddenColumnsIndices = hiddenColumnsIndices,
            model = model,
            rowView = holder.itemView
        )
    }
}