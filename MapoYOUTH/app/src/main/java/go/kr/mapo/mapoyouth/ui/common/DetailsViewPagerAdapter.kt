package go.kr.mapo.mapoyouth.ui.common

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import go.kr.mapo.mapoyouth.network.response.Organization
import go.kr.mapo.mapoyouth.util.ORGANIZATION

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-09-09
 * @desc
 */

class DetailsViewPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val fragment : Fragment,
    private val organization: Organization) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        val bundle = Bundle().apply {
            putSerializable(ORGANIZATION, organization)
        }
        val organizationDetailsFragment = OrganizationDetailsFragment().also {
            it.arguments = bundle
        }
        return when (position) {
            0 -> fragment
            else -> organizationDetailsFragment
        }
    }
}