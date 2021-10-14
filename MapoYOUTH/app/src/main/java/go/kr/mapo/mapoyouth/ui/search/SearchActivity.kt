package go.kr.mapo.mapoyouth.ui.search

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.ui.donation.DonationRecyclerViewAdapter
import go.kr.mapo.mapoyouth.ui.edu.EduListAdapter
import go.kr.mapo.mapoyouth.ui.edu.EduViewModel
import go.kr.mapo.mapoyouth.ui.volunteer.VolunteerListAdapter
import go.kr.mapo.mapoyouth.ui.volunteer.VolunteerViewModel
import go.kr.mapo.mapoyouth.ui.youth.YouthListAdapter
import go.kr.mapo.mapoyouth.ui.youth.YouthViewModel
import go.kr.mapo.mapoyouth.util.customView.CustomAttr

/**
 * @author LimSeulgi
 * @email sg21.lim@gmail.com
 * @created 2021-09-09
 * @desc
 **/

@AndroidEntryPoint
class SearchActivity: AppCompatActivity() {

    // Toolbar에는 Home(왼쪽 배치)과 menu(오른쪽 배치)가 존재함
    // HomeBtn을 BackBtn으로 변경진행

    // Fragment는 레이아웃 매니저와 어댑터를 생성하고 연결해주는 곳

    private lateinit var mToolbar: Toolbar
    private lateinit var tabLayout: TabLayout
    private lateinit var search_button : ImageButton
    private lateinit var search_start : TextView
    private lateinit var recyclerView : RecyclerView
    private lateinit var autoCompleteTextView : AutoCompleteTextView
    private lateinit var inputMethodManager : InputMethodManager

    private val youthViewModel : YouthViewModel by viewModels()
    private val youthAdapter by lazy { YouthListAdapter() }

    private val volunteerViewModel : VolunteerViewModel by viewModels()
    private val volunteerAdapter by lazy { VolunteerListAdapter() }

    private val eduViewModel : EduViewModel by viewModels()
    private val eduAdapter by lazy { EduListAdapter() }


    private var curTabPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        mToolbar = findViewById(R.id.search_toolbar)
        tabLayout = findViewById(R.id.tabLayout)
        search_button = findViewById(R.id.search_button)
        search_start = findViewById(R.id.search_start)
        recyclerView = findViewById(R.id.recyclerView)
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView)

        search_start.visibility = View.VISIBLE          //보임
        recyclerView.visibility = View.GONE               //숨기기


        val donationAdapter = DonationRecyclerViewAdapter(listOf("1", "1", "1", "1", "1"))

        with(recyclerView) {
            //레이아웃 매니저 셋팅 -> xml에서 진행함

            // Adapter 셋팅
            adapter = youthAdapter
        }


        val tabItem = tabLayout.getChildAt(0) as ViewGroup

        // Tab 클릭시 동작
        tabLayout.apply {
            getTabAt(0)!!.select().also { CustomAttr.changeTabsBold(tabItem, 0, tabCount) }
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.let {
                        curTabPosition = it.position
                        CustomAttr.changeTabsBold(tabItem, curTabPosition, tabLayout.tabCount
                        ) // 탭 선택시 글씨 굵게
                        recyclerView.adapter = when (curTabPosition) {
                            0 -> youthAdapter
                            1 -> volunteerAdapter
                            2 -> eduAdapter
                            else -> donationAdapter
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })
        }

        // 화면 뒤로가기 - Btn 생성
        setSupportActionBar(mToolbar).also { CustomAttr.commonSettingActionbar(supportActionBar) }

        // KBD 검색 Btn(Enter)로 검색시
        autoCompleteTextView.setOnKeyListener { _, keyCode, event ->
            if ((event.action== KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                search_start.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE

                requestSearch(autoCompleteTextView.text, curTabPosition)
                inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS) // 내용 입력 후 KBD Enter 클릭시 KBD 숨김

                true

            } else {
                false
            }
        }

        inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        // 화면 내 검색 Btn로 검색시
        search_button.setOnClickListener {
            requestSearch(autoCompleteTextView.text, curTabPosition)
                inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS) // 내용 입력 후 search_button 클릭시 KBD 숨김
        }
        subscribeToObservers()
    }

    private fun subscribeToObservers() {
        youthViewModel.youthSearchResult.observe(this, {
            recyclerView.adapter = youthAdapter
            youthAdapter.submitData(lifecycle, it)
        })

        volunteerViewModel.volunteerSearchResult.observe(this, {
            recyclerView.adapter = volunteerAdapter
            volunteerAdapter.submitData(lifecycle, it)
        })

        eduViewModel.eduSearchResult.observe(this, {
            recyclerView.adapter = eduAdapter
            eduAdapter.submitData(lifecycle, it)
        })

    }

    // 검색입력 검사
    private fun requestSearch(word : Editable, tabPosition: Int){
        if (word.isBlank()){
            Toast.makeText(this, "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        } else {    // 검색 요청
            val keyword = word.toString().trim()
            when(tabPosition) {
                0 -> youthViewModel.requestSearchYouth(keyword)  //청소년 활동 검색요청
                1 -> volunteerViewModel.requestSearchVolunteer(keyword)  // 봉사활동 검색요청
                2 -> eduViewModel.requestSearchEdu(keyword)             // 평생교육 검색요청

            }
            search_start.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
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
