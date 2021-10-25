package go.kr.mapo.mapoyouth.ui.home

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.FragmentHomeBinding
import go.kr.mapo.mapoyouth.ui.search.SearchActivity
import go.kr.mapo.mapoyouth.ui.setting.SettingActivity
import go.kr.mapo.mapoyouth.util.customView.CustomAttr
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private val viewModel : HomeViewModel by viewModels()

    private val homeYouthListAdapter by lazy { HomeYouthListAdapter() }
    private val homeVolunteerListAdapter by lazy { HomeVolunteerListAdapter() }
    private val homeEduListAdapter by lazy { HomeEduListAdapter() }
    private val homeDonationListAdapter by lazy { HomeDonationListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            fragment = this@HomeFragment
            return root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerViewAdapter()

        val tabItem = binding.tabs.getChildAt(0) as ViewGroup
        setupTabSelected(tabItem)
        setupNestedScrollView(tabItem)
    }

    private fun setupRecyclerViewAdapter() {
        with(binding) {
            rvYouth.adapter = homeYouthListAdapter
            rvVolunteer.adapter = homeVolunteerListAdapter
            rvEdu.adapter = homeEduListAdapter
            rvDonation.adapter = homeDonationListAdapter
        }
        subscribeToObservers()
    }

    private fun subscribeToObservers() {
        with(viewModel) {
            getNetworkState()
            isOnlined.observe(viewLifecycleOwner, { isOnlined ->
                binding.state = isOnlined
                if(isOnlined) {
                    viewLifecycleOwner.lifecycleScope.launch {
                        with(viewModel) {
                            getLatestYouth().observe(viewLifecycleOwner, {
                                homeYouthListAdapter.submitList(it)
                            })
                            getLatestVolunteer().observe(viewLifecycleOwner, {
                                homeVolunteerListAdapter.submitList(it)
                            })
                            getLatestEdu().observe(viewLifecycleOwner, {
                                homeEduListAdapter.submitList(it)
                            })
                            getDonation().observe(viewLifecycleOwner, {
                                homeDonationListAdapter.submitList(it)
                            })
                        }
                    }
                }
            })
        }
    }

    private fun setupTabSelected(tabItem: ViewGroup) {
        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let { autoScroll(tabItem, it.position) }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
                tab?.let { autoScroll(tabItem, it.position) }
            }
        })
    }

    private fun autoScroll(tabItem: ViewGroup, position : Int) {
        with(binding) {
            ObjectAnimator.ofInt(
                nestedScrollView, "scrollY", when (position) {
                    0 -> 0
                    1 -> titleVolunteer.top + 70
                    2 -> titleEdu.top + 70
                    else -> titleDonation.top + 70
                }
            ).apply {
                duration = 500L // 스크롤이 지속되는 시간을 설정한다. (1000 밀리초 == 1초)
            }.start()
            CustomAttr.changeTabsBold(tabItem, position, tabs.tabCount)
        }
    }

    private fun changeTabItemBold(tabItem: ViewGroup, position: Int, tabCount: Int) {
        for (i in 0 until tabCount) {
            tabItem.getChildAt(i).isSelected = i == position
            CustomAttr.changeTabsBold(tabItem, position, tabCount)
        }
    }

    private fun setupNestedScrollView(tabItem: ViewGroup) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.nestedScrollView.setOnScrollChangeListener { _, _, scrollY, _, _ ->
                setVisibilityDisplay(tabItem, scrollY)
            }
        }
    }

    private fun setVisibilityDisplay(tabItem: ViewGroup, scrollY: Int) {
        with(binding) {
            if(scrollY > 0 && scrollY < titleYouth.bottom) {
                searchLayout.visibility = View.VISIBLE
                topSearch.visibility = View.GONE
                tabLayout.visibility = View.GONE
                shadow.visibility = View.GONE
            } else {
                searchLayout.visibility = View.GONE
                topSearch.visibility = View.VISIBLE
                tabLayout.visibility = View.VISIBLE
                shadow.visibility = View.VISIBLE
            }
            changeTabItemBold(tabItem, when(scrollY) {
                in 0 until titleVolunteer.top -> 0
                in titleVolunteer.top until titleEdu.top -> 1
                in titleEdu.top until rvEdu.top+100-> 2
                else -> 3
            }, tabs.tabCount)
        }
    }

    fun setOnButtonClick(view: View) {
        when(view.id) {
            R.id.top_setting -> {
                val nextIntent = Intent(requireContext(), SettingActivity::class.java)
                startActivity(nextIntent)
            }
            R.id.top_search, R.id.et_search, R.id.btn_search -> {
                val nextIntent = Intent(requireContext(), SearchActivity::class.java)
                startActivity(nextIntent)
            }
        }
    }

}