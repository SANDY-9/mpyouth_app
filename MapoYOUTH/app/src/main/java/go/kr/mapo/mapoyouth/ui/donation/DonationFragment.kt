package go.kr.mapo.mapoyouth.ui.donation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.paging.filter
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.FragmentDonationBinding
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
        getDonationList()
        getDonationFilterList()
        setupToolbar()
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

    @SuppressLint("ResourceType")
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

            var category: String? = null
            when(category) {
                "전체" -> 0
                "디자인" -> 1
                "번역/외국어" -> 2
                "생활" -> 3
                "음악/영상" -> 4
                "프로그램 개발" -> 5
                "문서작성" -> 6
                else -> 7
            }
            val data = it
            donationAdapter.submitData(lifecycle, data)
            for (i in 0..7) {
                categoryArray[i].setOnCheckedChangeListener { button, isChecked ->
                    if(isChecked) {
                        var filterData = data

                        if( i > 0 ) filterData = data.filter { it.category.name == categoryArray[i].text }

                        button.setBackgroundResource(bgColorArray[i])
                        button.setTextColor(resources.getColor(colorArray[i]))
                        donationAdapter.submitData(lifecycle, filterData)
                    } else {
                        button.setBackgroundResource(R.drawable.bg_dona_btn)
                        button.setTextColor(resources.getColor(R.color.mapo_gray))
                    }
                }
            }
        })
    }
}