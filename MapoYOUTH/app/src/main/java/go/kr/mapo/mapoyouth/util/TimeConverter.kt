package go.kr.mapo.mapoyouth.util

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-10-01
 * @desc 방금전, 몇일전 시간 변환 유틸
 */

object TimeConverter {

    private const val SEC = 60
    private const val MIN = 60
    private const val HOUR = 24
    private const val DAY = 30
    private const val MONTH = 12
    private val dateFormat = SimpleDateFormat(TIME_FORMAT)
    private val dayFormat = SimpleDateFormat("EEEE", Locale.getDefault())

    fun formatTimeString(date : String): String {
        val currentTime = System.currentTimeMillis()
        val regTime = dateFormat.parse(date).time
        val diffTime = (currentTime - regTime) / 1000
        return when {
            diffTime < SEC -> "방금 전"
            diffTime/SEC < MIN -> "${diffTime/SEC}분 전"
            diffTime/SEC/MIN < HOUR -> "${diffTime/SEC/MIN}시간 전"
            diffTime/SEC/MIN/HOUR < DAY -> "${diffTime/SEC/MIN/HOUR}일 전"
            diffTime/SEC/MIN/HOUR/DAY < MONTH -> "${diffTime/SEC/MIN/HOUR/DAY}달 전"
            else -> "${diffTime/SEC/MIN/HOUR/DAY/MONTH}년 전"
        }
    }

    fun getDayOfTheWeek(date: String) : String {
        val calendar = Calendar.getInstance().also {
            it.time = dateFormat.parse(date)
        }
        return dayFormat.format(calendar.time)
    }

}