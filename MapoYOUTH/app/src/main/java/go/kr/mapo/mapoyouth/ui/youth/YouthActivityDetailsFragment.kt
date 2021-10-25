package go.kr.mapo.mapoyouth.ui.youth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.FragmentYouthActivityDetailsBinding

@AndroidEntryPoint
class YouthActivityDetailsFragment : Fragment() {

    private val viewModel : YouthViewModel by activityViewModels()
    private lateinit var binding : FragmentYouthActivityDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_youth_activity_details, container, false)
        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            return root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.youthDetails.observe(viewLifecycleOwner, {
            binding.youthDetails = it
        })
    }

}