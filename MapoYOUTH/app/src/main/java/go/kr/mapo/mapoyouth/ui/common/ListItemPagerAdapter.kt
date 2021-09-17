package go.kr.mapo.mapoyouth.ui.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.ui.edu.EduListAdapter
import go.kr.mapo.mapoyouth.ui.volunteer.VolunteerListAdapter
import go.kr.mapo.mapoyouth.ui.youth.YouthListAdapter
import go.kr.mapo.mapoyouth.util.FLAG_EDU
import go.kr.mapo.mapoyouth.util.FLAG_VOLUNTEER
import go.kr.mapo.mapoyouth.util.FLAG_YOUTH

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-09-16
 * @desc
 */
class ListItemPagerAdapter(private val flag : Int) : RecyclerView.Adapter<ListItemPagerAdapter.HolderView>() {

    inner class HolderView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recyclerView : RecyclerView = itemView.findViewById(R.id.recyclerView)
        fun setAdapter() {
            recyclerView.adapter = when(flag) {
                FLAG_YOUTH -> YouthListAdapter(listOf("","","","",""))
                FLAG_VOLUNTEER -> VolunteerListAdapter(listOf("","","","",""))
                FLAG_EDU -> EduListAdapter(listOf("","","","",""))
                else -> null
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderView =
        HolderView(LayoutInflater.from(parent.context).inflate(R.layout.item_pager_view, parent, false))

    override fun onBindViewHolder(holder: HolderView, position: Int) {
        holder.setAdapter()
    }

    override fun getItemCount(): Int = 5
}