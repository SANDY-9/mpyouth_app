package go.kr.mapo.mapoyouth.ui.search

import android.content.Context
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.FragmentHomeBinding
import go.kr.mapo.mapoyouth.databinding.FragmentSearchBinding
import go.kr.mapo.mapoyouth.ui.MainActivity
import go.kr.mapo.mapoyouth.ui.edu.EduListAdapter
import go.kr.mapo.mapoyouth.ui.volunteer.VolunteerListAdapter
import go.kr.mapo.mapoyouth.ui.youth.YouthListAdapter
import go.kr.mapo.mapoyouth.util.CustomAttr

/**
 * @author LimSeulgi
 * @email sg21.lim@gamil.com
 * @created 2021-09-09
 * @desc
 **/

class SearchFragment: Fragment(R.layout.fragment_search) {

    // Toolbar에는 Home(왼쪽 배치)과 menu(오른쪽 배치)가 존재함
    // HomeBtn을 BackBtn으로 변경진행

    // Fragment는 레이아웃 매니저와 어댑터를 생성하고 연결해주는 곳

    private lateinit var mToolbar: Toolbar
    private lateinit var tabLayout: TabLayout
    private lateinit var callback: OnBackPressedCallback

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)     // Fragment에 메뉴가 있다고 알려줌

        mToolbar = view.findViewById(R.id.search_toolbar)
        tabLayout = view.findViewById(R.id.tabLayout)


        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        val youthAdapter = YouthListAdapter(listOf("1", "1", "1", "1", "1"))
        val volunteerAdapter = VolunteerListAdapter(listOf("1", "1", "1", "1", "1"))
        val eduAdapter = EduListAdapter(listOf("1", "1", "1", "1", "1"))

        with(recyclerView) {

            //레이아웃 매니저 셋팅 -> xml에서 진행함

            // Adapter 셋팅
            adapter = youthAdapter

        }
        val tabItem = tabLayout.getChildAt(0) as ViewGroup

        // Tab 클릭시 동작
        tabLayout.apply {
            getTabAt(0)!!.select().also { CustomAttr.changeTabsBold(tabItem, 0, tabCount) }
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab : TabLayout.Tab?){
                    tab?.let {
                        val position = it.position
                        CustomAttr.changeTabsBold(tabItem, position, tabLayout.tabCount) // 탭 선택시 글씨 굵게
                        recyclerView.adapter = when (position){
                            0 -> youthAdapter
                            1 -> volunteerAdapter
                            2 -> eduAdapter
                            else -> null
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })
        }


        // 화면 뒤로가기
        Log.d("Backbtn", "BackBtn Start!")
        val parent = activity as MainActivity
        with(parent) {
            setSupportActionBar(mToolbar)
            supportActionBar!!.apply {
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)          // 뒤로가기 버튼, Default = true
                setHomeAsUpIndicator(R.drawable.ic_arrow_back)              // Toolbar의 홈버튼 이미지를 변경 (Default IMG = <- 모양 )
                title = null
            }
        }

    }

    // 화면 뒤로가기 - 클릭 이벤트 처리
    // 살려주세요 제발 동작 좀 해주세요....
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("Backbtn", "잘 출력되나 home")
        return when (item.itemId){
            android.R.id.home ->

                true
            else -> super.onOptionsItemSelected(item)

        }

        Log.d("Backbtn", "왜 동작을 안함?")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                TODO("Not yet implemented")
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onDestroy() {
        super.onDestroy()
        callback.remove()
    }


/*    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("Backbtn", "잘 출력되나 home")
        return when (item.itemId){
            android.R.id.home ->

                true
            else -> super.onOptionsItemSelected(item)

        }

        Log.d("Backbtn", "왜 동작을 안함?")
    }*/


}


// Fragment에서 OnCreate 사용할 필요 없음!!!
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setHasOptionsMenu(true)     // Fragment에 메뉴가 있다고 알려줌
//
//
//        val mToolbar = findViewById(R.id.menu_backbtn) as Toolbar
//        setSupportActionBar(mToolbar)
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
//
//
//         Fragment에서 getSupportActionBar를 어떻게 사용해야하나?
//        ((MainActivity) getActivity()).getSupportActionBar()
//
//
//    }



//        getSupportActionBar().setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
//
//
//        Fragment에서 getSupportActionBar를 어떻게 사용해야하나?
//        ((MainActivity) getActivity()).getSupportActionBar()


/*
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        return view
    }
*/


// Live Data
/*
    private lateinit var binding: FragmentSearchBinding
    private lateinit var toolbar: Toolbar

override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        toolbar = view.findViewById(R.id.toolbar2)

        binding = FragmentSearchBinding.inflate(layoutInflater)

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener {
            val fragmentManager = getActivity()?.getSupportFragmentManager();
            fragmentManager?.beginTransaction()?.remove(this)?.commit();
            fragmentManager?.popBackStack()
        }

        return binding.root
    }*/

