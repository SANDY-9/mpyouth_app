package go.kr.mapo.mapoyouth.ui.donation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.ActivityDonationDetailsBinding
import go.kr.mapo.mapoyouth.ui.common.DetailsViewPagerAdapter
import go.kr.mapo.mapoyouth.util.ID
import go.kr.mapo.mapoyouth.util.customView.CustomAttr

@AndroidEntryPoint
class DonationDetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDonationDetailsBinding
    private val viewModel: DonationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  DataBindingUtil.setContentView(this, R.layout.activity_donation_details)

        binding.lifecycleOwner = this@DonationDetailsActivity
        setSupportActionBar(binding.toolbar).also { CustomAttr.commonSettingActionbar(supportActionBar) }

        getDetails()
        observeDetails()
    }

    private fun getDetails() {
        val id = intent.getIntExtra(ID, -1)
        if(id != -1) {
            viewModel.setDonationDetails(id)
        }
    }

    private fun observeDetails() {
        val donationDetailActivity = DonationActivityDetailsFragment()
        viewModel.donationDetails.observe(this, {
            binding.donationDetails = it
            binding.viewPager.apply {
                adapter = DetailsViewPagerAdapter(
                    this@DonationDetailsActivity,
                    donationDetailActivity,
                    it.organization)
                currentItem =0
            }
            val tabLayoutTextArray = arrayOf("활동정보","기관정보")
            TabLayoutMediator(binding.tabLayout,binding.viewPager){tab,position->
                tab.text = tabLayoutTextArray[position]
            }.attach()
        })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_top_share, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
            R.id.menu_share -> CustomAttr.actionShare(this, "공유하기")
        }
        return super.onOptionsItemSelected(item)
    }
}