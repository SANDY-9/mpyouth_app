package go.kr.mapo.mapoyouth.ui.donation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.tabs.TabLayoutMediator
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.ActivityDonationDetailsBinding
import go.kr.mapo.mapoyouth.ui.common.DetailsViewPagerAdapter
import go.kr.mapo.mapoyouth.util.CustomAttr

class DonationDetailsActivity : AppCompatActivity() {

    lateinit var binding : ActivityDonationDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDonationDetailsBinding.inflate(layoutInflater).apply { setContentView(root) }
        setSupportActionBar(binding.toolbar).also { CustomAttr.commonSettingActionbar(supportActionBar) }
        binding.viewPager.apply {
            adapter = DetailsViewPagerAdapter(this@DonationDetailsActivity, DonationActivityDetailsFragment())
            currentItem = 0
        }

        val tabLayoutTextArray = arrayOf("활동정보","기관정보")
        TabLayoutMediator(binding.tabLayout,binding.viewPager){tab,position->
            tab.text = tabLayoutTextArray[position]
        }.attach()
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