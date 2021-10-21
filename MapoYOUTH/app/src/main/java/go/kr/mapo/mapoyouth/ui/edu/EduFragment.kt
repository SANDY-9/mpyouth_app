package go.kr.mapo.mapoyouth.ui.edu

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.FragmentEduBinding
import go.kr.mapo.mapoyouth.network.response.Edu
import go.kr.mapo.mapoyouth.network.response.Youth
import go.kr.mapo.mapoyouth.ui.search.SearchActivity
import go.kr.mapo.mapoyouth.ui.volunteer.VolunteerListAdapter

@AndroidEntryPoint
class EduFragment : Fragment() {

    private lateinit var binding: FragmentEduBinding
    private lateinit var eduAdapter: EduListAdapter
    private val viewModel: EduViewModel by viewModels()

    private var curPosition = 0

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
        setupToolbar()
        binding.recyclerView.adapter = eduAdapter
        connectSpinnerAdapter()
        subscribeToObservers()
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

    private fun connectSpinnerAdapter() {
        val spinnerAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner_list,
            R.layout.item_spinner
        ).also { it.setDropDownViewResource(R.layout.item_spinner) }
        binding.spinner.adapter = spinnerAdapter
    }

    private fun subscribeToObservers() {
        viewModel.eduList.observe(viewLifecycleOwner, {
            eduAdapter.submitData(lifecycle, getData(it, curPosition))
            whenSpinnerSelected(it)
        })
    }

    private fun whenSpinnerSelected(data: PagingData<Edu>) = binding.spinner.apply {
        onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                curPosition = position
                eduAdapter.submitData(lifecycle, getData(data, curPosition))
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }

    private fun getData(data: PagingData<Edu>, position: Int) = when(position) {
        1 -> data.filter { it.targetAge.contains("초") }
        2 -> data.filter { it.targetAge.contains("중") }
        3 -> data.filter { it.targetAge.contains("고") }
        4 -> data.filter { it.targetAge.contains("대") }
        5 -> data.filter { it.targetAge.contains("일반") }
        else -> data
    }

}

