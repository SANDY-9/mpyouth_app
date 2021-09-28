package go.kr.mapo.mapoyouth.ui.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.FragmentOnboardingBinding
import go.kr.mapo.mapoyouth.ui.onboard.screens.OnboardFirstScreen
import go.kr.mapo.mapoyouth.ui.onboard.screens.OnboardSecondScreen
import go.kr.mapo.mapoyouth.ui.onboard.screens.OnboardThirdScreen

class OnboardingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentOnboardingBinding.inflate(inflater, container, false)

        val fragList = arrayListOf<Fragment>(
            OnboardFirstScreen(),
            OnboardSecondScreen(),
            OnboardThirdScreen()
        )

        val adapter = OnboardViewPagerAdapter(
            fragList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.onboardingViewPager.adapter = adapter
        binding.dotsIndicator.setViewPager2(binding.onboardingViewPager)

        return binding.root
    }

}