package go.kr.mapo.mapoyouth.ui.donation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.FragmentDonationBinding

class DonationFragment : Fragment() {

    lateinit var binding :FragmentDonationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_donation, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.donationRv.apply {
            this.adapter = DonationRecyclerViewAdapter(listOf("1","2","3","4","5","6","7","8"))
            this.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL,false)
        }
    }

}