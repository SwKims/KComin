package com.ksw.comink

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ksw.comink.item.BannerItem
import kotlinx.android.synthetic.main.item_layout_banner.view.*

/**
 * Created by KSW on 2021-01-09
 */
class ViewPagerAdapter(private val interaction: Interaction) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val ITEM_COUNT = 5
    }

    private var bannerItemList: List<BannerItem>? = null

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder {
        return BannerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout_banner, parent, false),
            interaction
        )

    }

    override fun getItemCount(): Int {
        return ITEM_COUNT
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        bannerItemList.let { bannerItemList ->
            bannerItemList?.get(position)?.let { (holder as BannerViewHolder).bind(it) }
        }
    }

    fun submitList(list: List<BannerItem>?) {
        bannerItemList = list
        notifyDataSetChanged()
    }
}

class BannerViewHolder constructor(itemView: View, private val interaction: Interaction) : RecyclerView.ViewHolder(itemView) {
    fun bind(bannerItem: BannerItem) {
        itemView.setOnClickListener {
            interaction.onBannerItemClicked(bannerItem)
        }
        itemView.iv_bannerImage.setImageResource(bannerItem.image)
    }
}
