package go.kr.mapo.mapoyouth

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import go.kr.mapo.mapoyouth.util.TimeConverter
import java.text.SimpleDateFormat

private const val TAG = "AppApplication"

@HiltAndroidApp
class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}