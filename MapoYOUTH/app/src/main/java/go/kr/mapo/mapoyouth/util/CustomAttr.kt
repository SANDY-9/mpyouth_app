package go.kr.mapo.mapoyouth.util

import android.graphics.Typeface
import android.util.Log
import android.view.ViewGroup
import android.widget.TextView

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-09-16
 * @desc
 */

private const val TAG = "CustomAttr"

object CustomAttr {

    fun changeTabsBold(tabs: ViewGroup, selectPosition: Int, tabCount : Int) {
        for (j in 0 until tabCount) {
            val vgTab = tabs.getChildAt(j) as ViewGroup
            val tabViewChild = vgTab.getChildAt(1)
            if (tabViewChild is TextView) {
                Log.e(TAG, "changeTabsBold: ", )
                tabViewChild.typeface = if(j == selectPosition) Typeface.DEFAULT_BOLD else Typeface.DEFAULT
            }
        }
    }

}