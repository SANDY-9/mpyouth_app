package go.kr.mapo.mapoyouth.ui.setting

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.FragmentSettingBinding
import go.kr.mapo.mapoyouth.ui.MainActivity
import go.kr.mapo.mapoyouth.ui.MainActivity.Companion.BACKSTACK_FLAG
import go.kr.mapo.mapoyouth.ui.home.HomeFragment
import go.kr.mapo.mapoyouth.ui.search.SearchActivity

/**
 * @author LimSeulgi
 * @email sg21.lim@gamil.com
 * @created 2021-09-09
 * @desc
 **/

class SettingFragment: Fragment(R.layout.fragment_setting) {

    private lateinit var mToolbar: androidx.appcompat.widget.Toolbar
    private var _binding : FragmentSettingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.fragment_setting, container, false)
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root

/*        // 알림설정 Btn
        binding.btnNoticesetting.setOnClickListener {
            val nextIntent = Intent(requireContext(), SettingNoticeFragment::class.java)    //인텐트 생성
            startActivity(nextIntent)   // 화면전환 진행
        }*/

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)     // Fragment에 메뉴가 있다고 알려줌

        mToolbar = view.findViewById(R.id.setting_toolbar)      //OnboardingFragment binding이랑 충돌나는데, 해결방법에 이것밖에 없을까?

        val parent = activity as MainActivity

        with(parent){
            setSupportActionBar(mToolbar)
            supportActionBar!!.apply {
                setDisplayHomeAsUpEnabled(true)
                setHomeAsUpIndicator(R.drawable.ic_arrow_back)
                title = null
            }
        }

/*        // 알림설정 Btn - 21.09.24 기능 삭제
        binding.btnNoticesetting.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_settingNoticeFragment)
        }*/

        // 서비스 이용약관 Btn
        binding.btnTermsOfService.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_settingTermsOfServiceFragment)
            BACKSTACK_FLAG = true
        }

        // 오픈소스 라이선스

        binding.btnOpenSource.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_SettingOpenSourceFragment)
            BACKSTACK_FLAG = true
        }

    }


    // 액션버튼 클릭 이벤트 처리
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            findNavController().popBackStack()
            BACKSTACK_FLAG = false
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null             // binding 자원 미사용시 반환
    }
}