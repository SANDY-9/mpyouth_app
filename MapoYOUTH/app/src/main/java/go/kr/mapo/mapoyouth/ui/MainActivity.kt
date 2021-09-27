package go.kr.mapo.mapoyouth.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.ActivityMainBinding
import go.kr.mapo.mapoyouth.ui.home.HomeFragment
import go.kr.mapo.mapoyouth.ui.search.SearchActivity
import go.kr.mapo.mapoyouth.ui.setting.SettingFragment
import go.kr.mapo.mapoyouth.ui.setting.SettingNoticeFragment
import go.kr.mapo.mapoyouth.util.ONBOARD_FINISHED_STR
import go.kr.mapo.mapoyouth.util.ONBOARD_SHARED_PREF

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    private var pressedTime : Long = 0
    var BACKSTACK_FLAG = false

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MapoYOUTH)

        // onboard
//        if (!onBoardingFinished()) {
//            findNavController(R.id.navHostFragment).navigate(R.id.action_homeFragment_to_onboardingFragment)
//        } else {
//        }

        if (onBoardingFinished()) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater).apply {
                setContentView(root)

                // NavController initialize
                val navController = findNavController(R.id.navHostFragment)
                navBottom.setupWithNavController(navController)
            }
        } else {
            Toast.makeText(this, "!!OnBoarding NEVER played!!", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(this, R.id.navHostFragment).navigate(R.id.action_homeFragment_to_onboardingFragment)
        }


    }

    override fun onBackPressed() {
        when {
            BACKSTACK_FLAG -> {
                BACKSTACK_FLAG = false
                super.onBackPressed()
            }
            !BACKSTACK_FLAG -> {
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
        }
    }

    private fun onBoardingFinished(): Boolean {
        val sharedPref = getSharedPreferences(ONBOARD_SHARED_PREF, Context.MODE_PRIVATE)
        return sharedPref.getBoolean(ONBOARD_FINISHED_STR, false)
    }

}