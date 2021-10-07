package go.kr.mapo.mapoyouth.ui.setting

import android.content.res.AssetManager
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.ui.MainActivity.Companion.BACKSTACK_FLAG
import java.io.InputStream

/**
 * @author LimSeulgi
 * @email sg21.lim@gmail.com
 * @created 2021-09-24
 * @desc
 **/

// 서비스 이용약관

class SettingTermsOfServiceActivity : AppCompatActivity() {

    private lateinit var mToolbar: androidx.appcompat.widget.Toolbar
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_terms_of_service)

        mToolbar = findViewById(R.id.setting_terms_of_service_toolbar)

        // 화면 뒤로가기 - Btn 생성
        with(parent) {
            setSupportActionBar(mToolbar)
            supportActionBar!!.apply {
                setDisplayHomeAsUpEnabled(true)
                setHomeAsUpIndicator(R.drawable.ic_arrow_back)
                title = null
            }
        }


        // txt 파일 읽어오기
        textView = findViewById(R.id.textView_terms_of_service)

        val assetManager : AssetManager = resources.assets
        var inputStream:InputStream = assetManager.open("terms_of_service.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        //Log.e(TAG, "onCreate: $inputString", )
        textView.text = inputString

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            super.onBackPressed()
            BACKSTACK_FLAG = true
        }
        return super.onOptionsItemSelected(item)
    }

}





// Fragment
/*
private const val TAG = "SettingTermsOfServiceFr"

class SettingTermsOfServiceFragemnt : Fragment() {

    private lateinit var mToolbar: androidx.appcompat.widget.Toolbar
    lateinit var binding: FragmentSettingTermsOfServiceBinding
    private lateinit var textView: TextView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_setting_terms_of_service, container, false)
        textView = view.findViewById(R.id.textView_terms_of_service)
        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        // Toolbar
        mToolbar = view.findViewById(R.id.setting_terms_of_service_toolbar)

        val parent = activity as MainActivity
        with(parent){
            setSupportActionBar(mToolbar)
            supportActionBar!!.apply {
                setDisplayHomeAsUpEnabled(true)
                setHomeAsUpIndicator(R.drawable.ic_arrow_back)
                title = null
            }
        }

        textView = view.findViewById(R.id.textView_terms_of_service)

        val assetManager : AssetManager = resources.assets
        var inputStream:InputStream = assetManager.open("terms_of_service.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        Log.e(TAG, "onCreateView: $inputString", )
        textView.text = inputString

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            findNavController().popBackStack()
            BACKSTACK_FLAG = true
        }
        return super.onOptionsItemSelected(item)
    }

}*/
