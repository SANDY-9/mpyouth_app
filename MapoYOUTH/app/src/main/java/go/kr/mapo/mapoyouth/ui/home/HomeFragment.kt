package go.kr.mapo.mapoyouth.ui.home

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayout
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.FragmentHomeBinding
import android.os.Build
import android.util.Log
import android.view.MotionEvent

import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import go.kr.mapo.mapoyouth.ui.MainActivity
import go.kr.mapo.mapoyouth.ui.search.SearchActivity
import go.kr.mapo.mapoyouth.util.CustomAttr

private const val TAG = "HomeFragment"

class HomeFragment : Fragment() {

    lateinit var binding : FragmentHomeBinding

    private var mainActivity:MainActivity?= null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

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

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            val tabItem = tabs.getChildAt(0) as ViewGroup
            nestedScrollView.setOnScrollChangeListener { _, _, scrollY, _, _ ->
                when(scrollY) {
                    in 0 until titleVolunteer.top -> {
                        setVisibilityDisplay(true)
                        setTabItem(tabItem, 0, tabs.tabCount)
                    }
                    in titleVolunteer.top until titleEdu.top -> {
                        setVisibilityDisplay(false)
                        setTabItem(tabItem, 1, tabs.tabCount)
                    }
                    in titleEdu.top until rvEdu.top+100-> {
                        setVisibilityDisplay(false)
                        setTabItem(tabItem, 2, tabs.tabCount)
                    }
                    in rvEdu.top+100 until rvDonationAd.bottom -> {
                        setVisibilityDisplay(false)
                        setTabItem(tabItem, 3, tabs.tabCount)
                    }
                }
            }
            tabs.apply {
                CustomAttr.changeTabsBold(tabItem, 0, tabCount)
                addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        tab?.let {
                            autoScroll(it.position)
                            CustomAttr.changeTabsBold(tabItem, it.position, tabs.tabCount)
                        }
                    }
                    override fun onTabUnselected(tab: TabLayout.Tab?) {
                    }
                    override fun onTabReselected(tab: TabLayout.Tab?) {
                    }
                })
            }
            rvYouth.adapter = HomeYouthListAdapter(listOf("1","2","3","4","5"))
            rvVolunteer.adapter = HomeVolunteerListAdapter(listOf("1","2","3","4","5"))
            rvVolunteerAd.adapter = HomeVolunteerADAdapter(listOf("1","2","3","4","5"))
            rvEdu.adapter = HomeEduListAdapter(listOf("1","2","3","4","5"))
            rvDonation.adapter = HomeDonationListAdapter(listOf("1","2","3","4","5"))
            rvDonationAd.adapter = HomeDonationADAdapter(listOf("1","2","3","4","5"))
            
        }

    }

    private fun setVisibilityDisplay(boolean: Boolean) {
        with(binding) {
            if(boolean) {
                searchLayout.visibility = View.VISIBLE
                topSearch.visibility = View.GONE
            } else {
                searchLayout.visibility = View.GONE
                topSearch.visibility = View.VISIBLE
            }
        }
    }

    private fun setTabItem(tabs: ViewGroup, position: Int, tabCount: Int) {
        for (i in 0 until tabCount) {
            tabs.getChildAt(i).isSelected = i == position
            CustomAttr.changeTabsBold(tabs, position, tabCount)
        }
    }

    private fun autoScroll(position : Int) {
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

    fun setOnButtonClick(view: View) {
        when(view.id) {
            R.id.top_setting -> {
                mainActivity!!.BACKSTACK_FLAG = true
                findNavController().navigate(R.id.action_homeFragment_to_settingFragment)
            }
            R.id.top_search, R.id.et_search, R.id.btn_search -> {
                val nextIntent = Intent(requireContext(), SearchActivity::class.java)
                startActivity(nextIntent)

            }
        }
    }

}