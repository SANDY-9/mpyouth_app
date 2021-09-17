package go.kr.mapo.mapoyouth.ui.donation

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import go.kr.mapo.mapoyouth.R

class DonationRecyclerViewAdapter(private val itemList: List<String>) : RecyclerView.Adapter<DonationRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTv: TextView = itemView.findViewById(R.id.donationRvItemTitleTv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_donation_rv, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val donationData = itemList[position]
        holder.itemView.setOnClickListener {
            it.context.startActivity(Intent(it.context, DonationDetailsActivity::class.java))
        }
        with (holder) {
            titleTv.text = donationData
        }
    }

    override fun getItemCount() = itemList.count()
}