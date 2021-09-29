package go.kr.mapo.mapoyouth.ui.onboard.screens

import android.content.Context
import android.os.Bundle
import android.provider.SyncStateContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.FragmentFirstScreenBinding
import go.kr.mapo.mapoyouth.databinding.FragmentOnboardThirdScreenBinding
import go.kr.mapo.mapoyouth.ui.MainActivity
import go.kr.mapo.mapoyouth.util.ONBOARD_FINISHED_STR
import go.kr.mapo.mapoyouth.util.ONBOARD_SHARED_PREF

class OnboardThirdScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOnboardThirdScreenBinding.inflate(inflater, container, false)

        return binding.root
    }
}