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
import androidx.core.animation.doOnEnd
import androidx.core.widget.NestedScrollView


private const val TAG = "HomeFragment"

class HomeFragment : Fragment() {

    lateinit var binding : FragmentHomeBinding
    private var tabSelected = false
    private var scrolledEnd = false
    private var realTabSelecd = false

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
            nestedScrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if(!tabSelected) {
                    when (scrollY) {
                        in 0..titleVolunteer.top -> {
                            searchLayout.visibility = View.VISIBLE
                            topSearch.visibility = View.GONE
                            realTabSelecd = false
                            tabs.getTabAt(0)!!.select()
                        }
                        in titleVolunteer.top..titleEdu.top -> {
                            searchLayout.visibility = View.GONE
                            topSearch.visibility = View.VISIBLE
                            realTabSelecd = false
                            tabs.getTabAt(1)!!.select()
                        }
                        in titleEdu.top..titleDonation.top -> {
                            searchLayout.visibility = View.GONE
                            topSearch.visibility = View.VISIBLE
                            realTabSelecd = false
                            tabs.getTabAt(2)!!.select()
                        }
                        in titleDonation.top..rvDonationAd.bottom -> {
                            searchLayout.visibility = View.GONE
                            topSearch.visibility = View.VISIBLE
                            realTabSelecd = false
                            tabs.getTabAt(3)!!.select()
                        }
                    }
                }
                if(scrolledEnd) {
                    tabSelected = false
                    scrolledEnd = false
                }
            }
            tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.let {
                        Log.e(TAG, "onTabSelected: ", )
                        if (tabSelected) {
                            ObjectAnimator.ofInt(
                                nestedScrollView, "scrollY", when (it.position) {
                                    0 -> 0
                                    1 -> titleVolunteer.top + 70
                                    2 -> titleEdu.top + 70
                                    else -> titleDonation.top + 70
                                }
                            ).apply {
                                duration = 1000L // 스크롤이 지속되는 시간을 설정한다. (1000 밀리초 == 1초)
                                doOnEnd {
                                    tabSelected = true
                                    scrolledEnd = true
                                }
                            }.start()
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })

            rvYouth.adapter = HomeYouthListAdapter(listOf("1","2","3","4","5"))
            rvVolunteer.adapter = HomeVolunteerListAdapter(listOf("1","2","3","4","5"))
            rvVolunteerAd.adapter = HomeVolunteerADAdapter(listOf("1","2","3","4","5"))
            rvEdu.adapter = HomeEduListAdapter(listOf("1","2","3","4","5"))
            rvDonation.adapter = HomeDonationListAdapter(listOf("1","2","3","4","5"))
            rvDonationAd.adapter = HomeDonationADAdapter(listOf("1","2","3","4","5"))
        }
    }

}