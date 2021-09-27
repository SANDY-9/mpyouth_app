package go.kr.mapo.mapoyouth.ui.onboard.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.FragmentFirstScreenBinding

class OnboardFirstScreen : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFirstScreenBinding.inflate(inflater, container, false)

        val vp = activity?.findViewById<ViewPager2>(R.id.onboardingViewPager)

        binding.btnSkipScreen01.setOnClickListener {
            vp?.currentItem = 1
        }

        return binding.root
    }
}