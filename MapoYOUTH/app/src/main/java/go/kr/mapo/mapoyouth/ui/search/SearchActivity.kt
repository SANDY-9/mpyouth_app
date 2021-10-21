package go.kr.mapo.mapoyouth.ui.search

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.ui.donation.DonationRecyclerViewAdapter
import go.kr.mapo.mapoyouth.ui.donation.DonationViewModel
import go.kr.mapo.mapoyouth.ui.edu.EduListAdapter
import go.kr.mapo.mapoyouth.ui.edu.EduViewModel
import go.kr.mapo.mapoyouth.ui.volunteer.VolunteerListAdapter
import go.kr.mapo.mapoyouth.ui.volunteer.VolunteerViewModel
import go.kr.mapo.mapoyouth.ui.youth.YouthListAdapter
import go.kr.mapo.mapoyouth.ui.youth.YouthViewModel
import go.kr.mapo.mapoyouth.util.customView.CustomAttr
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * @author LimSeulgi
 * @email sg21.lim@gmail.com
 * @created 2021-09-09
 * @desc
 **/

@AndroidEntryPoint
class SearchActivity: AppCompatActivity() {

    private lateinit var mToolbar: Toolbar
    private lateinit var tabLayout: TabLayout
    private lateinit var search_button : ImageButton
    private lateinit var search_start : TextView
    private lateinit var recyclerView : RecyclerView
    private lateinit var autoCompleteTextView : AutoCompleteTextView
    private lateinit var inputMethodManager : InputMethodManager
    private lateinit var tabLayoutGroup : LinearLayout

    private val youthViewModel : YouthViewModel by viewModels()
    private val youthAdapter by lazy { YouthListAdapter() }

    private val volunteerViewModel : VolunteerViewModel by viewModels()
    private val volunteerAdapter by lazy { VolunteerListAdapter() }

    private val eduViewModel : EduViewModel by viewModels()
    private val eduAdapter by lazy { EduListAdapter() }

    private val donationViewModel : DonationViewModel by viewModels()
    private val donationAdapter by lazy { DonationRecyclerViewAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        init()
        // 상단바 설정
        setSupportActionBar(mToolbar).also { CustomAttr.commonSettingActionbar(supportActionBar) }

        setupTapState()
        setupSearchState()
        subscribeToObservers()
    }

    private fun init() {
        mToolbar = findViewById(R.id.search_toolbar)
        tabLayout = findViewById(R.id.tabLayout)
        search_button = findViewById(R.id.search_button)
        search_start = findViewById(R.id.search_start)
        recyclerView = findViewById(R.id.recyclerView)
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView)
        tabLayoutGroup = findViewById(R.id.tabLayoutGroup)

        inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    private var curTabPosition = 0
    private var isEverSearched = false

    private fun setupTapState() = tabLayout.apply {
        val tabItem = tabLayout.getChildAt(0) as ViewGroup
        CustomAttr.changeTabsBold(tabItem, 0, tabCount)
        // Tab 클릭시 동작
        addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    curTabPosition = it.position
                    CustomAttr.changeTabsBold(tabItem, curTabPosition, tabLayout.tabCount) // 탭 선택시 글씨 굵게
                    if(isEverSearched) {
                        requestSearch(autoCompleteTextView.text, curTabPosition)
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun setupSearchState(){
        // KBD 검색 Btn(Enter)로 검색시
        autoCompleteTextView.setOnKeyListener { _, keyCode, event ->
            when {
                event.action== KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER -> {
                    requestSearch(autoCompleteTextView.text, curTabPosition)
                    true
                }
                else -> false
            }
        }

        // 화면 내 검색 Btn로 검색시
        search_button.setOnClickListener {
            requestSearch(autoCompleteTextView.text, curTabPosition)
        }
    }

    // 검색입력 검사
    private fun requestSearch(word : Editable, tabPosition: Int){
        if (word.isBlank()){
            Toast.makeText(this, "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        } else {    // 검색 요청
            isEverSearched = true
            if(isEverSearched) tabLayoutGroup.visibility = View.VISIBLE
            val keyword = word.toString().trim()
            when(tabPosition) {
                0 -> youthViewModel.requestSearchYouth(keyword)             //청소년 활동 검색요청
                1 -> volunteerViewModel.requestSearchVolunteer(keyword)     //봉사활동 검색요청
                2 -> eduViewModel.requestSearchEdu(keyword)                 //평생교육 검색요청
                3 -> donationViewModel.requestSearchDonation(keyword)      // 재능기부 검색요청
            }
        }
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS) // 내용 입력 후 KBD Enter 클릭시 KBD 숨김
    }

    private fun subscribeToObservers() {
        youthViewModel.youthSearchResult.observe(this, {
            if(isEverSearched) {
                recyclerView.adapter = youthAdapter
                youthAdapter.submitData(lifecycle, it)
                lifecycleScope.launch {
                    youthAdapter.loadStateFlow.collect { state ->
                        if(state.append.endOfPaginationReached) {
                            setViewSearchAfter(youthAdapter.itemCount)
                        }
                    }
                }
            }
        })

        volunteerViewModel.volunteerSearchResult.observe(this, {
            if(isEverSearched) {
                recyclerView.adapter = volunteerAdapter
                volunteerAdapter.submitData(lifecycle, it)
                lifecycleScope.launch {
                    volunteerAdapter.loadStateFlow.collect { state ->
                        if(state.append.endOfPaginationReached) {
                            setViewSearchAfter(volunteerAdapter.itemCount)
                        }
                    }
                }
            }
        })

        eduViewModel.eduSearchResult.observe(this, {
            if(isEverSearched) {
                recyclerView.adapter = eduAdapter
                eduAdapter.submitData(lifecycle, it)
                lifecycleScope.launch {
                    eduAdapter.loadStateFlow.collect { state ->
                        if(state.append.endOfPaginationReached) {
                            setViewSearchAfter(eduAdapter.itemCount)
                        }
                    }
                }
            }
        })

        donationViewModel.donationSearchResult.observe(this, {
            if(isEverSearched) {
                recyclerView.adapter = donationAdapter
                donationAdapter.submitData(lifecycle, it)
                lifecycleScope.launch {
                    donationAdapter.loadStateFlow.collect { state ->
                        if(state.append.endOfPaginationReached) {
                            setViewSearchAfter(donationAdapter.itemCount)
                        }
                    }
                }
            }
        })

    }

    private fun setViewSearchAfter(itemCount: Int) {
        if(itemCount == 0) {
            search_start.visibility = View.VISIBLE
            search_start.text = "검색 결과가 없습니다."
        } else {
            search_start.visibility = View.GONE
        }
    }

    // 화면 뒤로가기 - 클릭 이벤트 처리
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            android.R.id.home -> {
                finish()            //뒤로가는 동작
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
