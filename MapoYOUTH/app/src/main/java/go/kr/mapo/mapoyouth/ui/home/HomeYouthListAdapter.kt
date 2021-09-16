package go.kr.mapo.mapoyouth.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import go.kr.mapo.mapoyouth.R

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-09-14
 * @desc
 */
class HomeYouthListAdapter(val list: List<String>): RecyclerView.Adapter<HomeYouthListAdapter.HolderView>() {

    inner class HolderView(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeYouthListAdapter.HolderView = HolderView(
        LayoutInflater.from(parent.context).inflate(R.layout.item_home_youth_rv, parent, false)
    )

    override fun onBindViewHolder(holder: HomeYouthListAdapter.HolderView, position: Int) {
    }

    override fun getItemCount(): Int = list.size
}