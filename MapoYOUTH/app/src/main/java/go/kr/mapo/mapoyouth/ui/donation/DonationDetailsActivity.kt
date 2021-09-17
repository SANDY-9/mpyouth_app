package go.kr.mapo.mapoyouth.ui.donation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.ActivityDonationDetailsBinding
import go.kr.mapo.mapoyouth.databinding.ActivityMainBinding
import go.kr.mapo.mapoyouth.ui.common.DetailsViewPagerAdapter

class DonationDetailsActivity : AppCompatActivity() {

    lateinit var binding : ActivityDonationDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDonationDetailsBinding.inflate(layoutInflater).apply { setContentView(root) }

        binding.viewPager.apply {
            adapter = DetailsViewPagerAdapter(this@DonationDetailsActivity, DonationActivityDetailsFragment())
            currentItem = 0
        }

        val tabLayoutTextArray = arrayOf("활동정보","기관정보")
        TabLayoutMediator(binding.tabLayout,binding.viewPager){tab,position->
            tab.text = tabLayoutTextArray[position]
        }.attach()

    }
}