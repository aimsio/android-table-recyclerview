package io.github.aimsio.tablelayout

import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.aimsio.tablelayout.TableViewAdapter
import io.github.aimsio.tablelayout.TableViewHolder

class TableHeaderAdapter(private val tableHeaderView: TableHeaderView) : TableViewAdapter() {

    private val hiddenColumnsIndexList: MutableList<Int> = mutableListOf()

    fun hideColumns(list: List<Int>) {
        hiddenColumnsIndexList.clear()
        hiddenColumnsIndexList.addAll(list)

        notifyDataSetChanged()
    }

    fun showAllColumns() {
        hiddenColumnsIndexList.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val view = tableHeaderView.createView(LayoutInflater.from(parent.context), parent)
        return TableViewHolder(view)
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        tableHeaderView.bindView(columnView = holder.itemView, hiddenColumnsIndices = hiddenColumnsIndexList)
    }
}