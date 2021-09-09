package go.kr.mapo.mapoyouth.ui.youth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.ui.common.DetailsViewPagerAdapter

class YouthDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youth_details)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        with(viewPager) {
            adapter = DetailsViewPagerAdapter(this@YouthDetailsActivity, YouthActivityDetailsFragment())
            currentItem = 0
        }
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when(position) {
                0 -> "활동정보"
                else -> "기관정보"
            }
        }.attach()
    }
}