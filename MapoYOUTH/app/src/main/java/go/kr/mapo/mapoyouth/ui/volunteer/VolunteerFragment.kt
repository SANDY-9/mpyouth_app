package go.kr.mapo.mapoyouth.ui.volunteer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.ui.youth.YouthListAdapter

class VolunteerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_volunteer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        with(recyclerView) {
            adapter = VolunteerListAdapter(listOf("1", "1", "1", "1", "1"))
        }
    }

}