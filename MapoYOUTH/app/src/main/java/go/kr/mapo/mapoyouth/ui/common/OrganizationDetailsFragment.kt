package go.kr.mapo.mapoyouth.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.FragmentOrganizationDetailsBinding
import go.kr.mapo.mapoyouth.network.response.Organization
import go.kr.mapo.mapoyouth.util.ORGANIZATION

class OrganizationDetailsFragment : Fragment() {

    private lateinit var organization : Organization
    private lateinit var binding : FragmentOrganizationDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        organization = arguments?.getSerializable(ORGANIZATION) as Organization
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_organization_details, container, false)
        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            organ = organization
            return root
        }
    }

}