package go.kr.mapo.mapoyouth.ui.setting

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.ui.MainActivity

/**
 * @author LimSeulgi
 * @email sg21.lim@gamil.com
 * @created 2021-09-09
 * @desc
 **/

class SettingFragment: Fragment(R.layout.fragment_setting) {

    private lateinit var mToolbar : androidx.appcompat.widget.Toolbar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)     // Fragment에 메뉴가 있다고 알려줌

        mToolbar = view.findViewById(R.id.setting_toolbar)


        val parent = activity as MainActivity
        with(parent){
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

    // HomeFragment의 top_setting 클릭시 SettingFragment로 이동
}