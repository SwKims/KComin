package com.ksw.comink

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by KSW on 2021-01-09
 */

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.setBannerItems(
            listOf(
                BannerItem(R.drawable.list4),
                BannerItem(R.drawable.list5),
                BannerItem(R.drawable.list6),
                BannerItem(R.drawable.list7),
                BannerItem(R.drawable.list8)
            )
        )

        menu.setOnClickListener(this)
        initViewPager()
        subscribeObservers()
    }

    private fun initViewPager() {
        viewPager.apply {
            viewPagerAdapter = ViewPagerAdapter()
            adapter = viewPagerAdapter
            // 배너 아래에 숫자의 변화를 보여준다.
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    tv_pageNumber.text = "${position+1}"
                }
            })
        }
    }

    // 뷰페이저에 5개의 화면을 띄운다.
    private fun subscribeObservers() {
        viewModel.bannerItemList.observe(this, Observer { bannerItemList ->
            viewPagerAdapter.submitList(bannerItemList)
        })
    }


    override fun onClick(position: View?) {

        position?.let {
            when (position.id) {
                R.id.menu -> {

                }
            }
        }

    }
}