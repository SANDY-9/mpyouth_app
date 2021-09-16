package go.kr.mapo.mapoyouth.util

import android.graphics.Typeface
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.forEachIndexed

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-09-16
 * @desc
 */
object CustomAttr {

    fun changeTabsBold(tabs: ViewGroup, selectPosition: Int, tabCount : Int) {
        for (j in 0 until tabCount) {
            val vgTab = tabs.getChildAt(j) as ViewGroup
            vgTab.forEachIndexed { index, _ ->
                val tabViewChild = vgTab.getChildAt(index)
                if (tabViewChild is TextView) {
                    tabViewChild.typeface = if(j == selectPosition) Typeface.DEFAULT_BOLD else Typeface.DEFAULT
                }
            }
        }
    }

}