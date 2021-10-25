package go.kr.mapo.mapoyouth.ui.onboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.FragmentOnboardingBinding
import go.kr.mapo.mapoyouth.ui.onboard.screens.OnboardFirstScreen
import go.kr.mapo.mapoyouth.ui.onboard.screens.OnboardSecondScreen
import go.kr.mapo.mapoyouth.ui.onboard.screens.OnboardThirdScreen
import go.kr.mapo.mapoyouth.util.ONBOARD_FINISHED_STR
import go.kr.mapo.mapoyouth.util.ONBOARD_SHARED_PREF

class OnboardingFragment : Fragment() {

    private lateinit var binding : FragmentOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragList = arrayListOf(
            OnboardFirstScreen(),
            OnboardSecondScreen(),
            OnboardThirdScreen()
        )

        val adapter = OnboardViewPagerAdapter(
            fragList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.onboardingViewPager.apply {
            this.adapter = adapter

            this.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.btnSkip.text = when (position) {
                        in 0..1 -> "건너뛰기"
                        else -> "완료"
                    }
                }
            })
        }

        binding.dotsIndicator.setViewPager2(binding.onboardingViewPager)

        binding.btnSkip.apply {
            this.setOnClickListener {
                findNavController().navigate(R.id.action_onboardingFragment_to_homeFragment)
                onBoardingFinished()
            }
        }
    }

    private fun onBoardingFinished() {
        val sharedPref = requireActivity().getSharedPreferences(ONBOARD_SHARED_PREF, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean(ONBOARD_FINISHED_STR, true)
        editor.apply()
    }
}