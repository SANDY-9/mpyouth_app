package go.kr.mapo.mapoyouth.ui.edu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.FragmentEduActivityDetailsBinding

class EduActivityDetailsFragment : Fragment() {

    private lateinit var binding : FragmentEduActivityDetailsBinding
    private val viewModel : EduViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edu_activity_details, container, false)
        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            return root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.eduDetails.observe(viewLifecycleOwner, {
            binding.eduDetails = it
        })
    }
}