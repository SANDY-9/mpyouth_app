package go.kr.mapo.mapoyouth.ui.volunteer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.FragmentVolunteerBinding
import go.kr.mapo.mapoyouth.ui.common.ListItemPagerAdapter
import go.kr.mapo.mapoyouth.util.FLAG_VOLUNTEER
import go.kr.mapo.mapoyouth.util.customView.CustomAttr

@AndroidEntryPoint
class VolunteerFragment : Fragment() {

    private lateinit var binding : FragmentVolunteerBinding
    private lateinit var pagerAdapter: ListItemPagerAdapter
    private val viewModel : VolunteerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_volunteer, container, false)
        pagerAdapter = ListItemPagerAdapter(null, VolunteerListAdapter())
        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            return root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            tabs.getTabAt(0)!!.select().also {
                val tabItem = tabs.getChildAt(0) as ViewGroup
                CustomAttr.changeTabsBold(tabItem, 0, tabs.tabCount)
            }
            viewPager.apply {
                currentItem = 0
                adapter = pagerAdapter
            }
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                val tabList = resources.getStringArray(R.array.volunteer_tab)
                tab.text = when(position) {
                    0 -> tabList[0]
                    1 -> tabList[1]
                    2 -> tabList[2]
                    3 -> tabList[3]
                    else -> tabList[4]
                }
            }.attach()
            viewModel.volunteerList.observe(viewLifecycleOwner, {
                pagerAdapter.submitVolunteerData(lifecycle, it)
            })
        }
    }

}