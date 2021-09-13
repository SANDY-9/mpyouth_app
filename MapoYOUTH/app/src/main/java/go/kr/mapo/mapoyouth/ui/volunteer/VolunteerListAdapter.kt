package go.kr.mapo.mapoyouth.ui.volunteer

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.ui.youth.YouthDetailsActivity

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-09-13
 * @desc
 */
class VolunteerListAdapter(val list: List<String>) : RecyclerView.Adapter<VolunteerListAdapter.HolderView>() {

    inner class HolderView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setBinding() {
            itemView.setOnClickListener {
                it.context.startActivity(Intent(it.context, VolunteerDetailsActivity::class.java))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderView =
        HolderView(LayoutInflater.from(parent.context).inflate(R.layout.item_volunteer_rv, parent, false))

    override fun onBindViewHolder(holder: HolderView, position: Int) {
        holder.setBinding()
    }

    override fun getItemCount(): Int = list.size
}