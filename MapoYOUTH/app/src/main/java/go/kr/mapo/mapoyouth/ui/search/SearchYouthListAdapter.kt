package go.kr.mapo.mapoyouth.ui.search

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.ui.youth.YouthDetailsActivity

// 1) ViewHolder라는 객체를 통해서 각 항목 나타냄
// 2) ViewHolder 위 inflate를 통해 내가 원하는 레이아웃을 표한하고 객체 반환 (onCreateViewHolder)
// 3) 거기에 데이터를 넣어줌 (onBindViewHolder)


class SearchYouthListAdapter(val list: List<String>) : RecyclerView.Adapter<SearchYouthListAdapter.HolderView>() {

    // 개별 아이템에 view 생성하여 제공
    inner class HolderView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setDivider() {
            itemView.setOnClickListener {
                it.context.startActivity(Intent(it.context, YouthDetailsActivity::class.java))
            }
        }
    }

    // 실제로 표현될 item view 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderView =

        // 내가 표현하고 싶은(코드로 조작하고 싶은) 레이아웃을 inflate하고
        HolderView(LayoutInflater.from(parent.context).inflate(R.layout.item_youth_rv, parent, false))

        // ViewHolder에 담아서 반환함
        // return HolderView(itemView)

    // item에 데이터를 바인딩한다. 화면에 들어온 아이템에 대해서만 작업을 진행
    override fun onBindViewHolder(holder: HolderView, position: Int) {
        holder.setDivider()
    }

    // item의 개수를 반환함
    override fun getItemCount(): Int = list.size
}
