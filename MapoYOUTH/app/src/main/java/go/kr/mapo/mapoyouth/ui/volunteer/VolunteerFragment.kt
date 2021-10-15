package go.kr.mapo.mapoyouth.ui.volunteer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.paging.PagingData
import androidx.paging.filter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.FragmentVolunteerBinding
import go.kr.mapo.mapoyouth.network.response.Volunteer
import go.kr.mapo.mapoyouth.network.response.Youth
import go.kr.mapo.mapoyouth.ui.common.ListItemPagerAdapter
import go.kr.mapo.mapoyouth.util.FLAG_VOLUNTEER
import go.kr.mapo.mapoyouth.util.customView.CustomAttr

@AndroidEntryPoint
class VolunteerFragment : Fragment() {

    private lateinit var binding : FragmentVolunteerBinding
    private lateinit var pagerAdapter: ListItemPagerAdapter
    private val viewModel : VolunteerViewModel by viewModels()

    private lateinit var categoryArray : Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_volunteer, container, false)
        categoryArray = resources.getStringArray(R.array.volunteer_category)
        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            return root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTabAndViewPager()
        subscribeToObservers()
    }

    private fun setupTabAndViewPager() {
        with(binding) {
            pagerAdapter = ListItemPagerAdapter(null, VolunteerListAdapter(), tabs.tabCount)
            tabs.getTabAt(0)!!.select().also {
                val tabItem = tabs.getChildAt(0) as ViewGroup
                CustomAttr.changeTabsBold(tabItem, 0, tabs.tabCount)
            }
            viewPager.apply {
                currentItem = 0
                adapter = pagerAdapter
            }
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                val tabList = resources.getStringArray(R.array.volunteer_category)
                tab.text = tabList[position]
            }.attach()
        }
    }

    private fun subscribeToObservers() {
        viewModel.volunteerList.observe(viewLifecycleOwner, {
            pagerAdapter.submitVolunteerData(lifecycle, it)
            whenTabSelected(it)
        })
    }

    private fun whenTabSelected(data: PagingData<Volunteer>) = binding.tabs.apply {
        addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                pagerAdapter.actionTopScroll()
                tab?.let {
                    pagerAdapter.submitVolunteerData(lifecycle, getData(data, selectedTabPosition))
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun getData(data: PagingData<Volunteer>, position: Int) =
        if(position == 0) data else data.filter { it.category.name == categoryArray[position] }

}