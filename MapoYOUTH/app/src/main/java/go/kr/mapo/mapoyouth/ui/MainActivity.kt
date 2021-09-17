package go.kr.mapo.mapoyouth.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.ActivityMainBinding
import go.kr.mapo.mapoyouth.ui.home.HomeFragment
import go.kr.mapo.mapoyouth.ui.search.SearchActivity
import go.kr.mapo.mapoyouth.ui.setting.SettingFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    private var pressedTime : Long = 0

    private var SettingFragment = SettingFragment()
    private var SearchActivity = SearchActivity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(root) }

        // NavController 정의
        with(binding) {
            val navController = findNavController(R.id.navHostFragment)
            navBottom.setupWithNavController(navController)
        }

    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() > pressedTime + 2000) {
            pressedTime = System.currentTimeMillis()
            Toast.makeText(this, "한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
            return
        }
        if (System.currentTimeMillis() <= pressedTime + 2000) {
            moveTaskToBack(true)                  // 태스크를 백그라운드로 이동
            finishAndRemoveTask()                 // 액티비티 종료 + 태스크 리스트에서 지우기
            android.os.Process.killProcess(android.os.Process.myPid()) // 앱 프로세스 종료
        }
    }



    fun openFragmnetOnFrameLayout(int:Int){
        val transaction = supportFragmentManager.beginTransaction()
        when(int){
            1 -> transaction.replace(R.id.container,SettingFragment)     // HomeFragmnet Setting Btn 클릭시 SettingFragment 추가
            //2 -> transaction.replace(R.id.container,SearchFragment)      // HomeFragmnet search 클릭시 SearchFragment 추가
        }
        transaction.commit()
    }
}