package go.kr.mapo.mapoyouth.ui.home

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
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
        val tabItem = binding.tabs.getChildAt(0) as ViewGroup
        setupTabSelected(tabItem)
        setupNestedScrollView(tabItem)
        setupRecyclerViewAdapter()
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
        viewLifecycleOwner.lifecycleScope.launch {
            with(viewModel) {
                getLatestYouth()!!.observe(viewLifecycleOwner, {
                    homeYouthListAdapter.submitList(it)
                })
                getLatestVolunteer()!!.observe(viewLifecycleOwner, {
                    homeVolunteerListAdapter.submitList(it)
                })
                getLatestEdu()!!.observe(viewLifecycleOwner, {
                    homeEduListAdapter.submitList(it)
                })
                getDonation()!!.observe(viewLifecycleOwner, {
                    homeDonationListAdapter.submitList(it)
                })
            }
        }
    }

    private fun setupTabSelected(tabItem: ViewGroup) {
        with(binding.tabs) {
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    Log.e("[네버엔딩]", "디버깅0")
                    tab?.let {
                        Log.e("[네버엔딩]", "디버깅1")
                        autoScroll(it.position)
                        CustomAttr.changeTabsBold(tabItem, it.position, tabCount)
                    }
                }
                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }
                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })
        }
    }

    private fun autoScroll(position : Int) {
        Log.e("[네버엔딩]", "디버깅2")
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
        }
    }

    private fun setupNestedScrollView(tabItem: ViewGroup) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            with(binding) {
                nestedScrollView.setOnScrollChangeListener { _, _, scrollY, _, _ ->
                    when(scrollY) {
                        in 0 until titleYouth.bottom -> {
                            setVisibilityDisplay(true)
                            changeTabItemBold(tabItem, 0, tabs.tabCount)
                        }
                        in titleYouth.bottom until titleVolunteer.top -> {
                            setVisibilityDisplay(false)
                            changeTabItemBold(tabItem, 0, tabs.tabCount)
                        }
                        in titleVolunteer.top until titleEdu.top -> {
                            setVisibilityDisplay(false)
                            changeTabItemBold(tabItem, 1, tabs.tabCount)
                        }
                        in titleEdu.top until rvEdu.top+100-> {
                            setVisibilityDisplay(false)
                            changeTabItemBold(tabItem, 2, tabs.tabCount)
                        }
                        in rvEdu.top+100 until bottomView.bottom -> {
                            setVisibilityDisplay(false)
                            changeTabItemBold(tabItem, 3, tabs.tabCount)
                        }
                    }
                }
            }
        }
    }

    private fun changeTabItemBold(tabs: ViewGroup, position: Int, tabCount: Int) {
        for (i in 0 until tabCount) {
            tabs.getChildAt(i).isSelected = i == position
            CustomAttr.changeTabsBold(tabs, position, tabCount)
        }
    }

    private fun setVisibilityDisplay(boolean: Boolean) {
        with(binding) {
            if(boolean) {
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