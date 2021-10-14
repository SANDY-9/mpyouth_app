package go.kr.mapo.mapoyouth.ui.edu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.FragmentEduBinding
import go.kr.mapo.mapoyouth.ui.volunteer.VolunteerListAdapter

@AndroidEntryPoint
class EduFragment : Fragment() {

    private lateinit var binding: FragmentEduBinding
    private lateinit var eduAdapter: EduListAdapter
    private val viewModel: EduViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edu, container, false)
        eduAdapter = EduListAdapter()
        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            return root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            recyclerView.adapter = eduAdapter
            viewModel.eduList.observe(viewLifecycleOwner, {
                eduAdapter.submitData(lifecycle, it)
            })
        }
    }
}

