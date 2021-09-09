package go.kr.mapo.mapoyouth.ui.youth

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import go.kr.mapo.mapoyouth.R

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-09-09
 * @desc
 */
class YouthListAdapter(val list: List<String>) : RecyclerView.Adapter<YouthListAdapter.HolderView>() {
    inner class HolderView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setDivider() {
            itemView.setOnClickListener {
                it.context.startActivity(Intent(it.context, YouthDetailsActivity::class.java))
            }
            when(adapterPosition) {
                list.lastIndex -> {
                    val divider = itemView.findViewById<View>(R.id.divider)
                    divider.visibility = View.GONE
                }
                2,3 -> {
                    /*
                    val tv = itemView.findViewById<TextView>(R.id.tv_deem)
                    val v = itemView.findViewById<View>(R.id.view_deem)
                    tv.visibility = View.VISIBLE
                    v.visibility = View.VISIBLE

                     */
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderView =
        HolderView(LayoutInflater.from(parent.context).inflate(R.layout.item_youth_rv, parent, false))

    override fun onBindViewHolder(holder: HolderView, position: Int) {
        holder.setDivider()
    }

    override fun getItemCount(): Int = list.size
}