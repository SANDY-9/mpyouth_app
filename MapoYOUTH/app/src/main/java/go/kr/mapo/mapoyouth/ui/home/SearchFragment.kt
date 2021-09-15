package go.kr.mapo.mapoyouth.ui.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.ui.MainActivity

class SearchFragment: Fragment(R.layout.fragment_search) {

    // Toolbar에는 Home(왼쪽 배치)과 menu(오른쪽 배치)가 존재함
    // HomeBtn을 BackBtn으로 변경진행

    private lateinit var mToolbar: Toolbar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)     // Fragment에 메뉴가 있다고 알려줌

        mToolbar = view.findViewById(R.id.search_toolbar)

        val parent = activity as MainActivity
        with(parent) {
            setSupportActionBar(mToolbar)
            supportActionBar!!.apply {
                setDisplayHomeAsUpEnabled(true)
                setHomeAsUpIndicator(R.drawable.ic_arrow_back)
                title = null
            }
        }
    }

    // 액션버튼 클릭 이벤트 처리
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            android.R.id.home -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
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

