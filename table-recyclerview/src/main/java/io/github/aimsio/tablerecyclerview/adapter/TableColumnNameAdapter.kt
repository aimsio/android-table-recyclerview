package io.github.aimsio.tablerecyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.aimsio.tablerecyclerview.view.TableViewHolder
import io.github.aimsio.tablerecyclerview.model.TableColumnNameView

/**
 *
 */
open class TableColumnNameAdapter(private val tableColumnNameView: TableColumnNameView) :
    RecyclerView.Adapter<TableViewHolder>() {

    private val hiddenColumnsIndices: MutableList<Int> = mutableListOf()

    open fun hideColumns(list: List<Int>) {
        hiddenColumnsIndices.clear()
        hiddenColumnsIndices.addAll(list)

        notifyDataSetChanged()
    }

    open fun showAllColumns() {
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