package go.kr.mapo.mapoyouth.ui.donation

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import go.kr.mapo.mapoyouth.databinding.ItemDonationRvBinding
import go.kr.mapo.mapoyouth.network.response.DonationListResponse
import go.kr.mapo.mapoyouth.util.ID

class DonationRecyclerViewAdapter : PagingDataAdapter<DonationListResponse.Data.Content, DonationRecyclerViewAdapter.ViewHolder>(DIFF_UTIL) {

    inner class ViewHolder(val binding: ItemDonationRvBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDonationRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        getItem(position)?.let { item ->
            with(holder.binding) {
//                val context = root.context
                lifecycleOwner = lifecycleOwner
                donation = item
                root.setOnClickListener {
                    val intent = Intent(it.context, DonationDetailsActivity::class.java).apply {
                        putExtra(ID, item.id)}
                    it.context.startActivity(intent)
                }
            }
        }
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<DonationListResponse.Data.Content>() {
            override fun areItemsTheSame(oldItem: DonationListResponse.Data.Content, newItem: DonationListResponse.Data.Content): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DonationListResponse.Data.Content, newItem: DonationListResponse.Data.Content): Boolean {
                return oldItem == newItem
            }
        }
    }
}