package go.kr.mapo.mapoyouth.ui.volunteer

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.PagingData
import androidx.paging.filter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.FragmentVolunteerBinding
import go.kr.mapo.mapoyouth.network.response.Volunteer
import go.kr.mapo.mapoyouth.ui.common.ListItemPagerAdapter
import go.kr.mapo.mapoyouth.ui.search.SearchActivity
import go.kr.mapo.mapoyouth.util.customView.CustomAttr

@AndroidEntryPoint
class VolunteerFragment : Fragment() {

    private lateinit var binding : FragmentVolunteerBinding
    private lateinit var pagerAdapter: ListItemPagerAdapter
    private val viewModel : VolunteerViewModel by viewModels()

    private lateinit var categoryArray : Array<String>

    private var curPosition = 0

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
        setupToolbar()
        setupTabAndViewPager()
        subscribeToObservers()
    }

    private fun setupToolbar() = binding.toolbar.apply {
        setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.menu_search -> {
                    startActivity(Intent(requireContext(), SearchActivity::class.java))
                    true
                }
                else -> false
            }
        }
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
            pagerAdapter.submitVolunteerData(lifecycle, getData(it, curPosition))
            whenTabSelected(it)
        })
    }

    private fun whenTabSelected(data: PagingData<Volunteer>) = binding.tabs.apply {
        addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                pagerAdapter.actionTopScroll()
                tab?.let {
                    curPosition = selectedTabPosition
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