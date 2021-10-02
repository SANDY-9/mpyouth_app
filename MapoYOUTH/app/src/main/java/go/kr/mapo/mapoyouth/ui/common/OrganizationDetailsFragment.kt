package go.kr.mapo.mapoyouth.ui.common

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.network.response.Organization
import go.kr.mapo.mapoyouth.util.ORGANIZATION

@AndroidEntryPoint
class OrganizationDetailsFragment : Fragment() {

    private lateinit var organization : Organization

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        organization = arguments?.getSerializable(ORGANIZATION) as Organization
        return inflater.inflate(R.layout.fragment_organization_details, container, false)
    }

}