package com.ksw.comink

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ksw.comink.item.GridItem
import kotlinx.android.synthetic.main.item_layout_grid.view.*

/**
 * Created by KSW on 2021-01-12
 */
class GridRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var gridItemList : List<GridItem>?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return GridItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout_grid, parent, false)
        )
    }


    override fun getItemCount(): Int {
        return gridItemList?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        gridItemList?.let {
            (holder as GridItemViewHolder).bind(it[position])
        }
    }

    fun submitList(list: List<GridItem>?) {
        gridItemList = list
        notifyDataSetChanged()
    }

    class GridItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(gridItem: GridItem) {
            itemView.iv_gridImage.setImageResource(gridItem.image)
            itemView.tv_gridTitle.text = gridItem.title
        }
    }
}