package go.kr.mapo.mapoyouth.ui.donation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.FragmentDonationBinding

@AndroidEntryPoint
class DonationFragment : Fragment() {

    private lateinit var binding :FragmentDonationBinding
    private var donationAdapter = DonationRecyclerViewAdapter()
    private val viewModel: DonationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_donation, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.recyclerView.apply {
            this.adapter = donationAdapter
            this.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL,false)
            viewModel.donationList.observe(viewLifecycleOwner, {
                donationAdapter.submitData(lifecycle, it)
            })
        }
    }


}