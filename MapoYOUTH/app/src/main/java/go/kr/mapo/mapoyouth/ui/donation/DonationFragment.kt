package go.kr.mapo.mapoyouth.ui.donation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.FragmentDonationBinding
import go.kr.mapo.mapoyouth.network.response.DonationListResponse
import go.kr.mapo.mapoyouth.ui.search.SearchActivity

@AndroidEntryPoint
class DonationFragment : Fragment() {

    private lateinit var binding :FragmentDonationBinding
    private var donationAdapter = DonationRecyclerViewAdapter()
    private val viewModel: DonationViewModel by viewModels()

    private var categoryArray: Array<RadioButton> = arrayOf()
    private var bgColorArray: Array<Int> = arrayOf()
    private var colorArray: Array<Int> = arrayOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_donation, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        getDonationList()
        getDonationFilterList()
    }

    private fun setupToolbar() = binding.toolbar.apply {
        setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.menu_search -> {
                    startActivity(Intent(requireContext(), SearchActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }

    private fun getDonationList() {
        binding.recyclerView.apply {
            this.adapter = donationAdapter
            this.layoutManager =
                LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private var curCategory = "전체"

    @Suppress("DEPRECATION")
    private fun getDonationFilterList() {
        categoryArray = arrayOf(binding.sltAll, binding.sltDesign, binding.sltLanguage,
            binding.sltLife, binding.sltMusic, binding.sltDevelop, binding.sltDoc, binding.sltBusiness)

        bgColorArray =  arrayOf(R.drawable.bg_donation_category_all, R.drawable.bg_donation_category_design,
            R.drawable.bg_donation_category_foreign, R.drawable.bg_donation_category_lifestyle,
            R.drawable.bg_donation_category_media, R.drawable.bg_donation_category_dev,
            R.drawable.bg_donation_category_docu, R.drawable.bg_donation_category_business)

        colorArray = arrayOf(R.color.mapo_blue, R.color.mapo_dona_design, R.color.mapo_dona_lang,R.color.mapo_dona_life,
            R.color.mapo_dona_music, R.color.mapo_navy, R.color.mapo_sky, R.color.mapo_bluegreen)

        viewModel.donationList.observe(viewLifecycleOwner, {

            donationAdapter.submitData(lifecycle, getData(it))

            for (i in 0..7) {
                categoryArray[i].setOnCheckedChangeListener { button, isChecked ->
                    if(isChecked) {
                        curCategory = categoryArray[i].text.toString()
                        button.setBackgroundResource(bgColorArray[i])
                        button.setTextColor(resources.getColor(colorArray[i]))
                        donationAdapter.submitData(lifecycle, getData(it))
                    } else {
                        button.setBackgroundResource(R.drawable.bg_dona_btn)
                        button.setTextColor(resources.getColor(R.color.mapo_gray))
                    }
                }
            }
        })
    }

    private fun getData(data: PagingData<DonationListResponse.Data.Content>) = when(curCategory) {
        "전체" -> data
        else -> data.filter { it.category.name == curCategory }
    }
}