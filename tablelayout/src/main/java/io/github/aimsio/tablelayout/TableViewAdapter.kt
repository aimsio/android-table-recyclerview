package io.github.aimsio.tablelayout

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class TableViewAdapter : RecyclerView.Adapter<TableViewHolder>()

class TableViewHolder(view: View) : RecyclerView.ViewHolder(view)