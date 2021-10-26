package go.kr.mapo.mapoyouth.ui.youth

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.ActivityYouthDetailsBinding
import go.kr.mapo.mapoyouth.ui.common.DetailsViewPagerAdapter
import go.kr.mapo.mapoyouth.util.ID
import go.kr.mapo.mapoyouth.util.customView.CustomAttr

@AndroidEntryPoint
class YouthDetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityYouthDetailsBinding
    private val viewModel : YouthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_youth_details)
        with(binding) {
            lifecycleOwner = this@YouthDetailsActivity
            youthViewModel = viewModel
            setSupportActionBar(toolbar).also { CustomAttr.commonSettingActionbar(supportActionBar) }
        }
        fetchYouthDetails()
        setInclude()
    }

    private fun fetchYouthDetails() {
        val programId = intent.getIntExtra(ID, -1)
        if(programId != -1) viewModel.setYouthDetails(programId)
    }

    private var title = ""

    private fun setInclude() {
        val youthDetailsFragment = YouthActivityDetailsFragment()
        with(binding.include) {
            viewModel.youthDetails.observe(this@YouthDetailsActivity, {
                it?.let {
                    title = it.title
                    viewPager.apply {
                        adapter = DetailsViewPagerAdapter(
                            this@YouthDetailsActivity,
                            youthDetailsFragment,
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
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_top_share, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
            R.id.menu_share -> CustomAttr.actionShare(this, title)
        }
        return super.onOptionsItemSelected(item)
    }
}