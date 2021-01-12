package com.ksw.comink

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by KSW on 2021-01-09
 */
class MainActivityViewModel : ViewModel() {
    private val _bannerItemList : MutableLiveData<List<BannerItem>> = MutableLiveData()

    val bannerItemList : LiveData<List<BannerItem>>
        get() = _bannerItemList

    fun setBannerItems(list: List<BannerItem>) {
        _bannerItemList.value = list
    }
}