package com.ksw.comink

import android.view.View

/**
 * Created by KSW on 2021-01-10
 */

// 배너 이미지 클릭시 동작이 이루어짐.
interface Interaction : View.OnClickListener{
    fun onBannerItemClicked(bannerItem: BannerItem)
}