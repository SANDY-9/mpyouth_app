package go.kr.mapo.mapoyouth.util.customView

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.text.Spannable
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.util.RECRUIT_STATUS_DONE
import go.kr.mapo.mapoyouth.util.RECRUIT_STATUS_RECRUITING
import go.kr.mapo.mapoyouth.util.TimeConverter
import go.kr.mapo.mapoyouth.util.VOLUNTEER_TYPE_INDIVIDUAL

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
    fun connectCallButton(button: Button, tel: String?) {
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
    fun connectURLButton(button: Button, url: String?) {
        button.setOnClickListener {
            val intent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse(url)
            }
            it.context.startActivity(intent)
        }
    }

    fun actionShare(context: Context, message: String?) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, message)
        }
        context.startActivity(Intent.createChooser(intent, "친구한테 공유하기"))
    }

    @JvmStatic
    @BindingAdapter("LoadingState")
    fun loadingProgress(frameLayout: FrameLayout, load:Boolean) {
        frameLayout.visibility = if(load) View.GONE else View.VISIBLE
    }

    @JvmStatic
    @BindingAdapter("ImageLoad")
    fun setImage(imageView: ImageView, url: String?) {
        Glide.with(imageView)
            .load(url)
            .placeholder(R.color.spinner_stroke)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("RecruitedLayout")
    fun setRecruited(frameLayout: FrameLayout, recruitedStatus: String?) {
        frameLayout.visibility = when(recruitedStatus) {
            RECRUIT_STATUS_RECRUITING -> View.GONE
            else -> View.VISIBLE
        }
    }

    @JvmStatic
    @BindingAdapter("RecruitedTextBox")
    fun setRecruited(textView: TextView, recruitedStatus: String?) {
        textView.visibility = when(recruitedStatus) {
            RECRUIT_STATUS_RECRUITING -> View.VISIBLE
            else -> View.GONE
        }
    }

    @JvmStatic
    @BindingAdapter("RecruitNumberText")
    fun setRecruitNumber(textView: TextView, number: Int?) {
        textView.text = "모집 인원: "+ if(number == 0 || number == null)"00명" else "${number}명"
    }

    @JvmStatic
    @BindingAdapter("RecruitNumberDetails")
    fun setRecruitNumberDetails(textView: TextView, number: Int?) {
        textView.text = if(number == 0 || number == null)"00명" else "${number}명"
    }

    @JvmStatic
    @BindingAdapter("RecruitButtonEnabled")
    fun buttonEnabled(button: Button, recruitedStatus: String?) {
        button.isEnabled = when(recruitedStatus) {
            RECRUIT_STATUS_RECRUITING -> true
            else -> false
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["recruitStart", "recruitEnd"])
    fun setRecruitPeriod(textView: TextView, recruitStart: String?, recruitEnd: String?) {
        val start = recruitStart?.substring(0, 10)
        val end = recruitEnd?.substring(5, 10)
        textView.text = if(start.isNullOrBlank() || end.isNullOrBlank())
            "-" else "$start ~ $end"
    }

    @JvmStatic
    @BindingAdapter(value = ["activityStart", "activityEnd"])
    fun setActivityPeriod(textView: TextView, activityStart: String?, activityEnd: String?) {
        val start = activityStart?.substring(0, 10)
        val end = activityEnd?.substring(5, 10)
        textView.text = if(!start.isNullOrBlank() && !end.isNullOrBlank())
            "$start(${TimeConverter.getDayOfTheWeek(activityStart)}) ~ " +
                "$end(${TimeConverter.getDayOfTheWeek(activityEnd)})"
        else "-"
    }

    @JvmStatic
    @BindingAdapter("EntryFee")
    fun setFee(textView: TextView, fee: Int?) {
        textView.text = if(fee == 0 || fee == null) "무료" else "${fee} 원"
    }

    @JvmStatic
    @BindingAdapter("Caution")
    fun setNotice(textView: TextView, notice: String?) {
        val resource = textView.context.resources
        textView.text = if(notice.isNullOrBlank()) resource.getString(R.string.detail_notice) else notice
    }

    @JvmStatic
    @BindingAdapter(value = ["startDate", "endDate"])
    fun setDateText(textView: TextView, startDate: String?, endDate: String?) {
        val str = if(startDate.isNullOrBlank() || endDate.isNullOrBlank()) "2021-01-01 ~ 2021-01-01"
            else startDate.substring(0, 10) + " ~ " + endDate.substring(0, 10)
        textView.apply {
            text = "일시 : $str"
            (text as Spannable).apply {
                setSpan(
                    ForegroundColorSpan(Color.parseColor("#676767")),
                    0,
                    3,
                    Spanned.SPAN_EXCLUSIVE_INCLUSIVE
                )
            }
        }
    }

    @JvmStatic
    @BindingAdapter("SetSpannable")
    fun setSpannable(textView: TextView, location: String?) {
        textView.apply {
            text = "장소 : $location"
            (text as Spannable).apply {
                setSpan(
                    ForegroundColorSpan(Color.parseColor("#676767")),
                    0,
                    3,
                    Spanned.SPAN_EXCLUSIVE_INCLUSIVE
                )
            }
        }
    }

    @JvmStatic
    @BindingAdapter("VolunteerType")
    fun setVolunteerType(textView: TextView, type: String?) {
        textView.text = when(type) {
            VOLUNTEER_TYPE_INDIVIDUAL -> "개인"
            else -> "단체"
        }
    }

    @JvmStatic
    @BindingAdapter("ActivityPeriod")
    fun setActivityPeriodText(textView: TextView, period: String?) {
        textView.text = period?.let { s->
            val split = s.split("|")
            var str = ""
            split.forEach { str += if(it == split.last()+1 || it == split[0]) "${it}요일" else ", ${it}요일" }
            str
        } ?: "-"
    }

    @JvmStatic
    @BindingAdapter("DetailsRecruitNumberText")
    fun setDetailsRecruitNumber(textView: TextView, number: Int?) {
        textView.text = if(number == 0 || number == null)"00명" else "${number}명"
    }

    @JvmStatic
    @BindingAdapter("TargetAge")
    fun setTargetAge(textView: TextView, targetAge: String?) {
        textView.visibility = if(targetAge!!.isNotBlank()) {
            val isContained = targetAge.contains(textView.text)
            if(isContained) View.VISIBLE else View.GONE
        } else {
            if(textView.text.contains("일반")) View.VISIBLE else View.GONE
        }
    }

    @JvmStatic
    @BindingAdapter("TargetAgeText")
    fun setTargetAgeText(textView: TextView, targetAge: String?) {
        targetAge?.let {
            if(targetAge.contains("|")) {
                textView.text = targetAge.replace("|", ", ")
            }
        }
    }

    @JvmStatic
    @BindingAdapter("YouthCategoryBox")
    fun setYouthCategoryBox(textView: TextView, category: String?) {
        category?.let {
            val color = ColorStateList.valueOf(Color.parseColor( when(it) {
                "건강/스포츠" -> "#A4C138"
                "모험개척" -> "#0396A6"
                "역사탐방" -> "#2FA894"
                "봉사협력" -> "#F28705"
                "과학정보" -> "#006699"
                "진로탐구" -> "#F2D22E"
                "자기개발" -> "#DC6B88"
                "문화예술" -> "#549DC6"
                "환경보존" -> "#007440"
                "교류" -> "#F2D22E"
                else -> "#666666"
            }))
            with(textView) {
                text = category
                setTextColor(color)
                backgroundTintList = color
            }
        }
    }

    @JvmStatic
    @BindingAdapter("VolunteerCategoryBox")
    fun setVolunteerCategoryBox(textView: TextView, category: String?) {
        category?.let {
            val color = ColorStateList.valueOf(Color.parseColor( when(it) {
                "지도(교육)봉사" -> "#91D8CA"
                "노력봉사" -> "#A1C410"
                "문화봉사" -> "#FF8989"
                "재능봉사" -> "#F28705"
                else -> "#F2D22E"
            }))
            with(textView) {
                text = "#$category"
                backgroundTintList = color
            }
        }
    }

    @JvmStatic
    @BindingAdapter("DonationCategoryBox")
    fun setDonationCategoryBox(textView: TextView, category: String?) {
        category?.let {
            val color = ColorStateList.valueOf(Color.parseColor( when(it) {
                "디자인" -> "#F28705"
                "번역/외국어" -> "#DC6B88"
                "생활" -> "#A4C138"
                "음악/영상" -> "#8503A6"
                "프로그램 개발" -> "#006699"
                "문서작성" -> "#549DC6"
                else -> "#2FA894"
            }))
            with(textView) {
                text = category
                setTextColor(color)
                backgroundTintList = color
            }
        }
    }

    @JvmStatic
    @BindingAdapter("MapImage")
    fun setMap(imageView: ImageView, name: String?) {
        imageView.setImageDrawable(imageView.context.resources.getDrawable(
            when(name) {
                "구립마포청소년문화의집" -> R.drawable.map_mapo_gu
                "시립마포청소년센터" -> R.drawable.map_mapo_si
                "망원청소년문화센터" -> R.drawable.map_mangwon
                else -> R.drawable.map_dohwa
            }, null)
        )
    }



}