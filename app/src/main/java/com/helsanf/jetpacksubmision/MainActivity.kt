package com.helsanf.jetpacksubmision

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.helsanf.jetpacksubmision.adapter.MainPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            view_pager_main.adapter = MainPagerAdapter(supportFragmentManager)
            tab_layout_main.setupWithViewPager(view_pager_main)
    }
}
