package go.kr.mapo.mapoyouth.ui.edu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.ActivityEduDetailsBinding
import go.kr.mapo.mapoyouth.ui.common.DetailsViewPagerAdapter
import go.kr.mapo.mapoyouth.ui.volunteer.VolunteerActivityDetailsFragment
import go.kr.mapo.mapoyouth.util.ID
import go.kr.mapo.mapoyouth.util.customView.CustomAttr

@AndroidEntryPoint
class EduDetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityEduDetailsBinding
    private val viewModel : EduViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edu_details)
        with(binding) {
            lifecycleOwner = this@EduDetailsActivity
            setSupportActionBar(toolbar).also { CustomAttr.commonSettingActionbar(supportActionBar) }
        }
        fetchVolunteerDetails()
        subscribeToObserver()
    }

    private fun fetchVolunteerDetails() {
        val id = intent.getIntExtra(ID, -1)
        if(id != -1) viewModel.setEduDetails(id)
    }

    private fun subscribeToObserver() {
        val eduActivityDetailsFragment = EduActivityDetailsFragment()
        viewModel.eduDetails.observe(this, {
            binding.eduDetails = it
            with(binding) {
                viewPager.apply {
                    adapter = DetailsViewPagerAdapter(
                        this@EduDetailsActivity,
                        eduActivityDetailsFragment,
                        it.organization)
                    currentItem = 0
                }
                TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                    tab.text = when(position) {
                        0 -> "활동정보"
                        else -> "기관정보"
                    }
                }.attach()
            }
        })
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