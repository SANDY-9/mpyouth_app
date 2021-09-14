package go.kr.mapo.mapoyouth.ui.donation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.FragmentDonationBinding

class DonationFragment : Fragment() {

    private lateinit var donationView: View

    private lateinit var rv: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        donationView = inflater.inflate(R.layout.fragment_donation, container, false)
        rv = donationView.findViewById(R.id.donationRv)

        with (rv) {
            this.layoutManager = LinearLayoutManager(donationView.context)
            this.adapter = DonationRecyclerViewAdapter(getDummyData())
        }

        return donationView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val manager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
//
//        with (binding.donationRv) {
//            layoutManager = manager
//            addItemDecoration(DividerItemDecoration(this.context,
//            LinearLayoutManager.VERTICAL))
//            adapter = DonationRecyclerViewAdapter(getDummyData())
//        }
    }


    private fun getDummyData(): MutableList<String> {
        val dummyData = mutableListOf<String>()
        dummyData.add("초,중학교 학습지원 희망해요! (전교과 가능)")
        dummyData.add("초,중학교 학습지원 희망해요! (전교과 가능)")
        dummyData.add("초,중학교 학습지원 희망해요! (전교과 가능)")
        dummyData.add("초,중학교 학습지원 희망해요! (전교과 가능)")
        dummyData.add("초,중학교 학습지원 희망해요! (전교과 가능)")
        dummyData.add("초,중학교 학습지원 희망해요! (전교과 가능)")
        dummyData.add("초,중학교 학습지원 희망해요! (전교과 가능)")
        dummyData.add("초,중학교 학습지원 희망해요! (전교과 가능)")
        dummyData.add("초,중학교 학습지원 희망해요! (전교과 가능)")
        dummyData.add("초,중학교 학습지원 희망해요! (전교과 가능)")
        dummyData.add("초,중학교 학습지원 희망해요! (전교과 가능)")
        dummyData.add("초,중학교 학습지원 희망해요! (전교과 가능)")
        dummyData.add("초,중학교 학습지원 희망해요! (전교과 가능)")
        dummyData.add("초,중학교 학습지원 희망해요! (전교과 가능)")
        dummyData.add("초,중학교 학습지원 희망해요! (전교과 가능)")

        return dummyData
    }
}