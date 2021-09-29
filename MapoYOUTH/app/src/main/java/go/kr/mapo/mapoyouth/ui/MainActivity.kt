package go.kr.mapo.mapoyouth.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.ActivityMainBinding
import go.kr.mapo.mapoyouth.util.ONBOARD_FINISHED_STR
import go.kr.mapo.mapoyouth.util.ONBOARD_SHARED_PREF

class MainActivity : AppCompatActivity() {

    companion object {
        var BACKSTACK_FLAG = false
    }

    lateinit var binding : ActivityMainBinding
    private var pressedTime : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MapoYOUTH)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)}

        // NavController 정의
        val navController = findNavController(R.id.navHostFragment)
        binding.navBottom.setupWithNavController(navController)

        if (onBoardingFinished()) {
            navController.navigate(R.id.action_onboardingFragment_to_homeFragment)
        } else {
            binding.navBottom.visibility = View.GONE
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