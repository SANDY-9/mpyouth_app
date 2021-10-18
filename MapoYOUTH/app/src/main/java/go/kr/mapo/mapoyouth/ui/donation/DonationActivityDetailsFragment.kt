package go.kr.mapo.mapoyouth.ui.donation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.FragmentDonationActivityDetailsBinding

class DonationActivityDetailsFragment : Fragment() {

    private lateinit var binding : FragmentDonationActivityDetailsBinding
    private  val viewModel: DonationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_donation_activity_details, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.donationDetails.observe(viewLifecycleOwner, {
            binding.donationDetails = it
        })
    }

}