package com.ksw.comink

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ksw.comink.item.BannerItem
import com.ksw.comink.item.GridItem

/**
 * Created by KSW on 2021-01-09
 */
class MainActivityViewModel : ViewModel() {
    private val _bannerItemList: MutableLiveData<List<BannerItem>> = MutableLiveData()
    private val _currentPosition: MutableLiveData<Int> = MutableLiveData()
    // add
    private val _gridItemList: MutableLiveData<List<GridItem>> = MutableLiveData()

    val bannerItemList: LiveData<List<BannerItem>>
        get() = _bannerItemList
    val currentPosition: LiveData<Int>
        get() = _currentPosition
    val gridItemList: LiveData<List<GridItem>>
        get() = _gridItemList

    init {
        _currentPosition.value = 0
    }

    fun setBannerItems(list: List<BannerItem>) {
        _bannerItemList.value = list
    }

    fun setGridItems(list: List<GridItem>) {
        _gridItemList.value = list
    }

    fun setCurrentPosition(position: Int) {
        _currentPosition.value = position
    }

    fun getCurrentPosition() = _currentPosition.value
}