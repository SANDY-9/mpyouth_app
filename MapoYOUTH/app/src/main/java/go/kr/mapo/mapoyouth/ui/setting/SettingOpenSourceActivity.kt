package go.kr.mapo.mapoyouth.ui.setting


import android.content.res.AssetManager
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import go.kr.mapo.mapoyouth.R
import java.io.InputStream

/**
 * @author LimSeulgi
 * @email sg21.lim@gmail.com
 * @created 2021-09-24
 * @desc
 **/

// 오픈소스 라이선스

class SettingOpenSourceActivity : AppCompatActivity() {

    private lateinit var mToolbar: Toolbar
    private lateinit var textView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_open_source)

        mToolbar = findViewById(R.id.setting_open_source_toolbar)


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
        textView = findViewById(R.id.textView_open_source)

        val assetManager : AssetManager = resources.assets
        var inputStream:InputStream = assetManager.open("open_source.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        textView.text = inputString

    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            super.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}



// SettingOpenSourceFragment
/*
class SettingOpenSourceFragment : Fragment() {

    private lateinit var mToolbar: Toolbar
    lateinit var binding : FragmentSettingOpenSourceBinding
    private lateinit var textView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_setting_open_source, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        // Toolbar
        mToolbar = view.findViewById(R.id.setting_open_source_toolbar)

        val parent = activity as MainActivity
        with(parent){
            setSupportActionBar(mToolbar)
            supportActionBar!!.apply {
                setDisplayHomeAsUpEnabled(true)
                setHomeAsUpIndicator(R.drawable.ic_arrow_back)
                title = null
            }
        }

        // txt 파일 읽어오기
        textView = view.findViewById(R.id.textView_open_source)

        val assetManager : AssetManager = resources.assets
        var inputStream:InputStream = assetManager.open("open_source.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
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
