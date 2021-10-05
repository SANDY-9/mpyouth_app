package go.kr.mapo.mapoyouth.ui.volunteer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.ActivityVolunteerDetailsBinding
import go.kr.mapo.mapoyouth.network.response.Organization
import go.kr.mapo.mapoyouth.ui.common.DetailsViewPagerAdapter
import go.kr.mapo.mapoyouth.ui.youth.YouthActivityDetailsFragment
import go.kr.mapo.mapoyouth.util.customView.CustomAttr

class VolunteerDetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityVolunteerDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_volunteer_details)
        with(binding) {
            setSupportActionBar(toolbar).also { CustomAttr.commonSettingActionbar(supportActionBar) }
            with(viewPager) {
                adapter = DetailsViewPagerAdapter(this@VolunteerDetailsActivity, VolunteerActivityDetailsFragment(), Organization())
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_top_share, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
}