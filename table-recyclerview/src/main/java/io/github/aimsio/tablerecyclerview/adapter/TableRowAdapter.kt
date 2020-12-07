package io.github.aimsio.tablerecyclerview.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.github.aimsio.tablerecyclerview.model.TableModel
import io.github.aimsio.tablerecyclerview.model.TableRowView
import io.github.aimsio.tablerecyclerview.view.TableViewHolder
import kotlinx.coroutines.*

/**
 * The adapter for building and displaying row in [TableRecyclerView]
 */
open class TableRowAdapter(private val tableRowView: TableRowView) :
    RecyclerView.Adapter<TableViewHolder>() {

    companion object {
        private const val TAG = "tableRecyclerView"
    }

    private val currentList: MutableList<TableModel> = mutableListOf()

    private val hiddenColumnsIndices: MutableList<Int> = mutableListOf()

    open fun updateData(newList: List<TableModel>) {

        val coroutineScope = CoroutineScope(Dispatchers.IO)
        val adapter = this

        coroutineScope.launch {

            try {
                val diffCallback = TableModelDiffCallback(
                    oldList = currentList,
                    newList = newList,
                    tableRowView = tableRowView
                )

                val diffResult = DiffUtil.calculateDiff(diffCallback)

                withContext(Dispatchers.Main) {
                    currentList.clear()
                    currentList.addAll(newList)

                    diffResult.dispatchUpdatesTo(adapter)
                    coroutineScope.cancel()
                }
            } catch (e: Exception) {
                Log.d(TAG, "error in updatedData: ${e.message}")
            }
        }
    }

    open fun hideColumns(list: List<Int>) {
        hiddenColumnsIndices.clear()
        hiddenColumnsIndices.addAll(list)

        notifyDataSetChanged()
    }

    open fun showAllColumns() {
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