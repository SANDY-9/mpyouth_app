package go.kr.mapo.mapoyouth.ui.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.ActivitySearchBinding
import go.kr.mapo.mapoyouth.ui.MainActivity
import go.kr.mapo.mapoyouth.ui.donation.DonationDetailsActivity
import go.kr.mapo.mapoyouth.ui.donation.DonationRecyclerViewAdapter
import go.kr.mapo.mapoyouth.ui.edu.EduListAdapter
import go.kr.mapo.mapoyouth.ui.volunteer.VolunteerListAdapter
import go.kr.mapo.mapoyouth.ui.youth.YouthListAdapter
import go.kr.mapo.mapoyouth.util.customView.CustomAttr

/**
 * @author LimSeulgi
 * @email sg21.lim@gmail.com
 * @created 2021-09-09
 * @desc
 **/

class SearchActivity: AppCompatActivity() {

    // Toolbar에는 Home(왼쪽 배치)과 menu(오른쪽 배치)가 존재함
    // HomeBtn을 BackBtn으로 변경진행

    // Fragment는 레이아웃 매니저와 어댑터를 생성하고 연결해주는 곳

    private lateinit var mToolbar: Toolbar
    private lateinit var tabLayout: TabLayout
    private lateinit var search_button : ImageButton
    private lateinit var search_start : TextView
    private lateinit var search_end : RecyclerView
    private lateinit var autoCompleteTextView : AutoCompleteTextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        mToolbar = findViewById(R.id.search_toolbar)
        tabLayout = findViewById(R.id.tabLayout)
        search_button = findViewById(R.id.search_button)
        search_start = findViewById(R.id.search_start)
        search_end = findViewById(R.id.recyclerView)
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView)

        search_start.visibility = View.VISIBLE          //보임
        search_end.visibility = View.GONE               //숨기기


        val youthAdapter = YouthListAdapter()
        val volunteerAdapter = VolunteerListAdapter(listOf("1", "1", "1", "1", "1"))
        val eduAdapter = EduListAdapter(listOf("1", "1", "1", "1", "1"))
        val donationAdapter = DonationRecyclerViewAdapter(listOf("1", "1", "1", "1", "1"))

        with(search_end) {
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
                        val position = it.position
                        CustomAttr.changeTabsBold(tabItem, position, tabLayout.tabCount
                        ) // 탭 선택시 글씨 굵게
                        search_end.adapter = when (position) {
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
                search_end.visibility = View.VISIBLE

/*                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)*/

                true

            } else {
                false
            }
        }

        // 화면 내 검색 Btn로 검색시
        search_button.setOnClickListener {
            requestSearch(autoCompleteTextView.text)
        }

    }

    // 검색입력 검사
    private fun requestSearch(word : Editable){
        if (word.isBlank()){
            Toast.makeText(this, "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
        } else {    // 검색 요청

            search_start.visibility = View.GONE
            search_end.visibility = View.VISIBLE



/*            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(View.search_end, 0)*/
        }
    }

    // 1. search_button 누르면 키보드 내려가도록 구현
    // 2. 키보드 내 엔터 클릭시 Toast 메시지, 내용 입력 후 클릭시 넘어가도록 구현

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
