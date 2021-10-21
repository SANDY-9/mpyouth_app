package go.kr.mapo.mapoyouth.ui.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.network.response.Volunteer
import go.kr.mapo.mapoyouth.network.response.Youth
import go.kr.mapo.mapoyouth.ui.volunteer.VolunteerListAdapter
import go.kr.mapo.mapoyouth.ui.youth.YouthListAdapter

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-09-16
 * @desc
 */


class ListItemPagerAdapter(
    private val youthListAdapter: YouthListAdapter?,
    private val volunteerListAdapter: VolunteerListAdapter?,
    private val tabCount: Int) : RecyclerView.Adapter<ListItemPagerAdapter.HolderView>() {

    lateinit var recyclerView: RecyclerView

    inner class HolderView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            recyclerView = itemView.findViewById(R.id.recyclerView)
        }
        fun setAdapter() {
            recyclerView.adapter = youthListAdapter ?: volunteerListAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HolderView(LayoutInflater.from(parent.context).inflate(R.layout.item_pager_view, parent, false))

    override fun onBindViewHolder(holder: HolderView, position: Int) {
        holder.setAdapter()
    }

    override fun getItemCount(): Int = tabCount

    fun submitYouthData(lifecycle: Lifecycle, pagingData: PagingData<Youth>) {
        youthListAdapter?.let { it.submitData(lifecycle, pagingData) }
    }

    fun submitVolunteerData(lifecycle: Lifecycle, pagingData: PagingData<Volunteer>) {
        volunteerListAdapter?.let { it.submitData(lifecycle, pagingData) }
    }

    fun actionTopScroll() {
        recyclerView.smoothScrollToPosition(0)
    }
}