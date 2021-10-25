package go.kr.mapo.mapoyouth.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-10-01
 * @desc 방금전, 몇일전 시간 변환 유틸
 */

object TimeConverter {

    private val dateFormat = SimpleDateFormat(TIME_FORMAT)
    private val dayFormat = SimpleDateFormat("EEEE", Locale.getDefault())

    fun getDayOfTheWeek(date: String) : String {
        val calendar = Calendar.getInstance().also {
            it.time = dateFormat.parse(date)
        }
        return dayFormat.format(calendar.time)
    }

}