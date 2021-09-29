package go.kr.mapo.mapoyouth.ui.youth

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.ActivityYouthDetailsBinding
import go.kr.mapo.mapoyouth.ui.common.DetailsViewPagerAdapter
import go.kr.mapo.mapoyouth.util.CustomAttr


class YouthDetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityYouthDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_youth_details)
        with(binding) {
            setSupportActionBar(toolbar).also { CustomAttr.commonSettingActionbar(supportActionBar) }
            with(include) {
                viewPager.apply {
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
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_top_share, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
            R.id.menu_share -> CustomAttr.actionShare(this, "공유할 내용")
        }
        return super.onOptionsItemSelected(item)
    }
}