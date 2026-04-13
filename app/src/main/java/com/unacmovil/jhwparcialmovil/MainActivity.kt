package com.unacmovil.jhwparcialmovil

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
		val viewPager = findViewById<ViewPager2>(R.id.viewPager)

		viewPager.adapter = MainPagerAdapter(this)

		TabLayoutMediator(tabLayout, viewPager) { tab, position ->
			tab.text = when (position) {
				0 -> getString(R.string.unit_1_tab)
				1 -> getString(R.string.unit_2_tab)
				else -> getString(R.string.unit_3_tab)
			}
		}.attach()
	}
}