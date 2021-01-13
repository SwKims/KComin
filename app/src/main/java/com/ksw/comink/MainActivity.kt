package com.ksw.comink

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenResumed
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.ksw.comink.item.BannerItem
import com.ksw.comink.item.GridItem
import com.ksw.comink.item.collapse
import com.ksw.comink.item.data.BannerItemList
import com.ksw.comink.item.data.GridItemList
import com.ksw.comink.item.expand
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.grid_main_recyclerview
import kotlinx.android.synthetic.main.activity_main.tv_pageNumber
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by KSW on 2021-01-09
 */

class MainActivity : AppCompatActivity(), View.OnClickListener, Interaction {

    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var gridRecyclerViewAdapter : GridRecyclerViewAdapter
    // 멈춤, 재실행 가능하드록, 다른앱 사용하다가 다시 켰을 경우
    private var isRunning = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.setBannerItems(BannerItemList)
        viewModel.setGridItems(GridItemList)

        tv_see_detail.setOnClickListener(this)
        iv_arrow.setOnClickListener(this)

        menu.setOnClickListener(this)
        initViewPager()
        subscribeObservers()
        autoScrollViewPager()
    }

    private fun initViewPager() {
        viewPager.apply {
            viewPagerAdapter = ViewPagerAdapter(this@MainActivity)
            adapter = viewPagerAdapter
            // 배너 아래에 숫자의 변화를 보여준다.
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    isRunning = true
                    this@apply.tv_pageNumber?.text = "${position+1}"

                    // 사용자가 직접 스크롤
                    viewModel.setCurrentPosition(position)
                }
            })
        }

        grid_main_recyclerview.apply {
            gridRecyclerViewAdapter = GridRecyclerViewAdapter()
            layoutManager = GridLayoutManager(this@MainActivity, 4)

            adapter = gridRecyclerViewAdapter
        }
    }

    // 뷰페이저에 5개의 화면을 띄운다.
    private fun subscribeObservers() {
        viewModel.bannerItemList.observe(this, Observer { bannerItemList ->
            viewPagerAdapter.submitList(bannerItemList)
        })

        viewModel.currentPosition.observe(this, Observer { currentPosition ->
            viewPager.currentItem = currentPosition
        })

        viewModel.gridItemList.observe(this, Observer { gridItemList ->
            gridRecyclerViewAdapter.submitList(gridItemList)
        })

    }

    override fun onBannerItemClicked(bannerItem: BannerItem) {
        startActivity(Intent(this@MainActivity, EventActivity::class.java))

    }

    // 비동기 처리인 코루틴 사용, destroy 되지 않는 이상 코루틴은 존재하므로 oncreate 에서 한번만 호출!
    private fun autoScrollViewPager() {
        lifecycleScope.launchWhenResumed {
            whenResumed {
                while (isRunning) {
                    delay(3000)
                    viewModel.getCurrentPosition()?.let {
                        viewModel.setCurrentPosition((it.plus(1)) % 5)
                    }
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        isRunning = false
    }

    override fun onResume() {
        super.onResume()
        isRunning = true
    }


    override fun onClick(position: View?) {

        position?.let {
            when (position.id) {
                R.id.menu -> {

                }
                R.id.tv_see_detail, R.id.iv_arrow -> {
                    if (ll_detail.visibility == View.GONE) {
                        ll_detail.expand(scrollView = scroll)
                        tv_see_detail.text = "닫기"
                        iv_arrow.setImageResource(R.drawable.ic_arrow_drop_up)
                    } else {
                        ll_detail.collapse()
                        tv_see_detail.text = "자세히보기"
                        iv_arrow.setImageResource(R.drawable.ic_arrow_drop_down_circle)
                    }
                }
            }
        }

    }
}