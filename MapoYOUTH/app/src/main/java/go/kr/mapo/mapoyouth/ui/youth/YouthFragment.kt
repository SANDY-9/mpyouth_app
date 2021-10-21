package go.kr.mapo.mapoyouth.ui.youth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.PagingData
import androidx.paging.filter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.FragmentYouthBinding
import go.kr.mapo.mapoyouth.network.response.Youth
import go.kr.mapo.mapoyouth.ui.common.ListItemPagerAdapter
import go.kr.mapo.mapoyouth.util.customView.CustomAttr

@AndroidEntryPoint
class YouthFragment : Fragment() {

    private lateinit var binding : FragmentYouthBinding
    private lateinit var pagerAdapter: ListItemPagerAdapter
    private val viewModel : YouthViewModel by viewModels()

    private var curCategory : String = "전체보기"
    private var curSorted : String = "전체"

    private lateinit var sortedArray : Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_youth, container, false)
        sortedArray = resources.getStringArray(R.array.spinner_list)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTabAndViewPager()
        connectSpinnerAdapter()
        subscribeToObservers()
    }

    private fun setupTabAndViewPager() {
        with(binding) {
            pagerAdapter = ListItemPagerAdapter(YouthListAdapter(), null, tabs.tabCount)
            tabs.getTabAt(0)!!.select().apply {
                val tabItem = tabs.getChildAt(0) as ViewGroup
                CustomAttr.changeTabsBold(tabItem, 0, tabs.tabCount)
            }
            viewPager.apply {
                currentItem = 0
                adapter = pagerAdapter
            }
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                val tabList = resources.getStringArray(R.array.youth_category)
                tab.text = tabList[position]
            }.attach()
        }
    }

    private fun connectSpinnerAdapter() {
        val spinnerAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner_list,
            R.layout.item_spinner
        ).also { it.setDropDownViewResource(R.layout.item_spinner) }
        binding.spinner.adapter = spinnerAdapter
    }

    private fun subscribeToObservers() {
        viewModel.youthList.observe(viewLifecycleOwner, {
            pagerAdapter.submitYouthData(lifecycle, it)
            whenTabSelected(it)
            whenSpinnerSelected(it)
        })
    }

    private fun whenTabSelected(data: PagingData<Youth>) = binding.tabs.apply {
        addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                pagerAdapter.actionTopScroll()
                tab?.let { selectedTab ->
                    curCategory = selectedTab.text.toString()
                    pagerAdapter.submitYouthData(lifecycle, when(selectedTab.position) {
                        0 -> if(curSorted == "전체") data else data.filter { it.targetAge.contains(curSorted) }
                        else -> {
                            data.filter {
                                when(curSorted) {
                                    "전체" -> it.category.name == curCategory
                                    else -> it.category.name == curCategory && it.targetAge.contains(curSorted)
                                }
                            }
                        }
                    })
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun whenSpinnerSelected(data: PagingData<Youth>) = binding.spinner.apply {
        onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                curSorted = when(position) {
                    1 -> "초"
                    2 -> "중"
                    3 -> "고"
                    4 -> "대"
                    else -> sortedArray[position]
                }
                pagerAdapter.submitYouthData(lifecycle, when(position) {
                    0 -> if(curCategory == "전체보기") data else data.filter { it.category.name == curCategory }
                    else -> {
                        data.filter {
                            when(curCategory) {
                                "전체보기" -> it.targetAge.contains(curSorted)
                                else -> it.targetAge.contains(curSorted) && it.category.name == curCategory
                            }
                        }
                    }
                })
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }

}