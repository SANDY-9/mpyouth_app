package go.kr.mapo.mapoyouth.util

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.util.Log
import android.view.MenuItem
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.databinding.BindingAdapter
import com.google.android.material.tabs.TabLayout
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.util.customView.CustomSpinner

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
                tabViewChild.typeface = if(j == selectPosition) Typeface.DEFAULT_BOLD else Typeface.DEFAULT
            }
        }
    }

    @JvmStatic
    @BindingAdapter("DefaultSelectedTab")
    fun defaultSelectedTab(tabs: TabLayout, boolean: Boolean) {
        if (boolean) {
            val tabItem = tabs.getChildAt(0) as ViewGroup
            changeTabsBold(tabItem, 0, tabs.tabCount)
        }
    }

    fun commonSettingActionbar(actionBar: ActionBar?) {
        with(actionBar!!) {
            title = null
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }
    }

    @JvmStatic
    @BindingAdapter("SpinnerEventListener")
    fun setSpinnerEventsListener(spinner: CustomSpinner, boolean: Boolean) {
        if(boolean) {
            spinner.apply {
                setSpinnerEventsListener(object : CustomSpinner.OnSpinnerEventsListener {
                    override fun onPopupWindowOpened(spinner: Spinner?) {
                        setBackgroundResource(R.drawable.bg_spinner_open)
                    }

                    override fun onPopupWindowClosed(spinner: Spinner?) {
                        setBackgroundResource(R.drawable.bg_spinner_close)
                    }
                })
            }
        }
    }

    @JvmStatic
    @BindingAdapter("Call-Connection")
    fun connectCallButton(button: Button, tel: String) {
        button.setOnClickListener {
            val intent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse("tel:$tel")
            }
            it.context.startActivity(intent)
        }
    }

    @JvmStatic
    @BindingAdapter("Url-Connection")
    fun connectURLButton(button: Button, url: String) {
        button.setOnClickListener {
            val intent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse(url)
            }
            it.context.startActivity(intent)
        }
    }

    fun actionShare(context: Context, message: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, message)
        }
        context.startActivity(Intent.createChooser(intent, "친구한테 공유하기"))
    }

}