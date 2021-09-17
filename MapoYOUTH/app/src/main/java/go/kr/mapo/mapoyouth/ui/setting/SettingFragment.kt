package go.kr.mapo.mapoyouth.ui.setting

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.FragmentHomeBinding
import go.kr.mapo.mapoyouth.databinding.FragmentSettingBinding
import go.kr.mapo.mapoyouth.ui.MainActivity
import go.kr.mapo.mapoyouth.ui.home.HomeFragment
import go.kr.mapo.mapoyouth.ui.search.SearchActivity

/**
 * @author LimSeulgi
 * @email sg21.lim@gamil.com
 * @created 2021-09-09
 * @desc
 **/

class SettingFragment: Fragment(R.layout.fragment_setting) {

    private lateinit var mToolbar : androidx.appcompat.widget.Toolbar
    lateinit var binding : FragmentSettingBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_setting, container, false)

        // 알림설정 Btn
        binding.btnNoticesetting.setOnClickListener {
            val nextIntent = Intent(requireContext(), SettingNoticeFragment::class.java)
            startActivity(nextIntent)
        }
    }


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
        if(item.itemId == android.R.id.home) findNavController().navigate(R.id.action_settingFragment_to_homeFragment)
        return super.onOptionsItemSelected(item)
    }


}