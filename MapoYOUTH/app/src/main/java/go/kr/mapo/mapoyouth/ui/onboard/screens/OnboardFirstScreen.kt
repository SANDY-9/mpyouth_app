package go.kr.mapo.mapoyouth.ui.onboard.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.FragmentFirstScreenBinding
import go.kr.mapo.mapoyouth.ui.MainActivity
import go.kr.mapo.mapoyouth.util.ONBOARD_FINISHED_STR
import go.kr.mapo.mapoyouth.util.ONBOARD_SHARED_PREF

class OnboardFirstScreen : Fragment() {

    private lateinit var owner : MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        owner = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFirstScreenBinding.inflate(inflater, container, false)

        binding.btnSkipScreen01.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFragment_to_homeFragment)
            onBoardingFinished()
            owner.binding.navBottom.visibility = View.VISIBLE
        }

        return binding.root
    }

    private fun onBoardingFinished() {
        val sharedPref = requireActivity().getSharedPreferences(ONBOARD_SHARED_PREF, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean(ONBOARD_FINISHED_STR, true)
        editor.apply()
    }
}