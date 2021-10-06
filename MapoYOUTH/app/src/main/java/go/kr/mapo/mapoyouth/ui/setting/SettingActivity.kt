package go.kr.mapo.mapoyouth.ui.setting


import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.ActivitySettingBinding
import go.kr.mapo.mapoyouth.ui.MainActivity.Companion.BACKSTACK_FLAG
import go.kr.mapo.mapoyouth.util.CustomAttr

/**
 * @author LimSeulgi
 * @email sg21.lim@gmail.com
 * @created 2021-09-09
 * @desc
 **/

class SettingActivity: AppCompatActivity() {

    private lateinit var mToolbar: androidx.appcompat.widget.Toolbar
    lateinit var btnTermsOfService : Button
    lateinit var btnOpenSource : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)


        // 화면 뒤로가기 - Btn 생성
        mToolbar = findViewById(R.id.setting_toolbar)      // OnboardingFragment binding이랑 충돌나는데, 해결방법에 이것밖에 없을까?
        btnTermsOfService = findViewById(R.id.btn_terms_of_service)
        btnOpenSource = findViewById(R.id.btn_open_source)

        with(parent) {
            setSupportActionBar(mToolbar)
            supportActionBar!!.apply {
                setDisplayHomeAsUpEnabled(true)
                setHomeAsUpIndicator(R.drawable.ic_arrow_back)
                title = null
            }
        }

        // 서비스 이용약관 Btn
        btnTermsOfService.setOnClickListener {
            val nextIntent = Intent(this, SettingTermsOfServiceActivity::class.java)    //인텐트 생성
            startActivity(nextIntent)   // 화면전환 진행
        }

        // 오픈소스 라이선스 Btn
            btnOpenSource.setOnClickListener {
            val nextIntent = Intent(this, SettingOpenSourceActivity::class.java)    //인텐트 생성
            startActivity(nextIntent)   // 화면전환 진행
        }

    }


    // 액션버튼 클릭 이벤트 처리
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            super.onBackPressed()
            BACKSTACK_FLAG = false
        }
        return super.onOptionsItemSelected(item)
    }
}


// SettingFragment
/*class SettingFragment: Fragment(R.layout.fragment_setting) {

    private lateinit var mToolbar: androidx.appcompat.widget.Toolbar
    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.fragment_setting, container, false)
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root

*//*        // 알림설정 Btn
        binding.btnNoticesetting.setOnClickListener {
            val nextIntent = Intent(requireContext(), SettingNoticeFragment::class.java)    //인텐트 생성
            startActivity(nextIntent)   // 화면전환 진행
        }*//*

    }
}



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)     // Fragment에 메뉴가 있다고 알려줌

        mToolbar =
            view.findViewById(R.id.setting_toolbar)      //OnboardingFragment binding이랑 충돌나는데, 해결방법에 이것밖에 없을까?

        val parent = activity as MainActivity

        with(parent) {
            setSupportActionBar(mToolbar)
            supportActionBar!!.apply {
                setDisplayHomeAsUpEnabled(true)
                setHomeAsUpIndicator(R.drawable.ic_arrow_back)
                title = null
            }
        }
    }*/

/*        // 알림설정 Btn - 21.09.24 기능 삭제
        binding.btnNoticesetting.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_settingNoticeFragment)
        }*//*


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
}*/
