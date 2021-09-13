package go.kr.mapo.mapoyouth.ui.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-09-09
 * @desc
 */
class DetailsViewPagerAdapter(fragmentActivity: FragmentActivity, private val fragment : Fragment) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        when(position) {
            0 -> fragment
            else -> OrganizationDetailsFragment()
        }
}