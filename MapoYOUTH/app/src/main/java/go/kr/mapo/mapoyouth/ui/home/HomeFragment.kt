package go.kr.mapo.mapoyouth.ui.home

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayout
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.FragmentHomeBinding
import android.graphics.Typeface
import android.os.Build

import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.core.animation.doOnEnd
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.forEachIndexed
import androidx.core.view.get
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.MutableLiveData
import go.kr.mapo.mapoyouth.util.CustomAttr
import kotlin.math.log


private const val TAG = "HomeFragment"

class HomeFragment : Fragment() {

    lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
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
                getTabAt(0)!!.select().also { CustomAttr.changeTabsBold(tabItem, 0, tabCount) }
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

}