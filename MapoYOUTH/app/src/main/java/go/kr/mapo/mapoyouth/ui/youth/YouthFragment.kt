package go.kr.mapo.mapoyouth.ui.youth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.FragmentYouthBinding
import go.kr.mapo.mapoyouth.ui.common.ListItemPagerAdapter
import go.kr.mapo.mapoyouth.util.CustomAttr
import go.kr.mapo.mapoyouth.util.FLAG_YOUTH

private const val TAG = "YouthFragment"

@AndroidEntryPoint
class YouthFragment : Fragment() {

    private lateinit var binding : FragmentYouthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_youth, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            val tabList = resources.getStringArray(R.array.youth_tab)
            viewPager.apply {
                currentItem = 0
                adapter = ListItemPagerAdapter(FLAG_YOUTH)
            }
            val tabItem = tabs.getChildAt(0) as ViewGroup
            tabs.getTabAt(0)!!.select()
                .apply { CustomAttr.changeTabsBold(tabItem, 0, tabs.tabCount) }
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.text = when (position) {
                    0 -> tabList[0]
                    1 -> tabList[1]
                    2 -> tabList[2]
                    3 -> tabList[3]
                    else -> tabList[4]
                }
            }.attach()
        }
    }

}