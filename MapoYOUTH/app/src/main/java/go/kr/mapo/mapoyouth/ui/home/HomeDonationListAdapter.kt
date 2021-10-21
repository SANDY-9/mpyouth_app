package go.kr.mapo.mapoyouth.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import go.kr.mapo.mapoyouth.databinding.ItemHomeDonationRvBinding
import go.kr.mapo.mapoyouth.network.response.DonationListResponse
import go.kr.mapo.mapoyouth.ui.donation.DonationDetailsActivity
import go.kr.mapo.mapoyouth.util.ID

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-09-14
 * @desc
 */
class HomeDonationListAdapter() : RecyclerView.Adapter<HomeDonationListAdapter.MyViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<DonationListResponse.Data.Content>() {
        override fun areItemsTheSame(oldItem: DonationListResponse.Data.Content, newItem: DonationListResponse.Data.Content): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DonationListResponse.Data.Content, newItem: DonationListResponse.Data.Content): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<DonationListResponse.Data.Content>) {
        differ.submitList(list)
    }

    inner class MyViewHolder(val binding: ItemHomeDonationRvBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = ItemHomeDonationRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.binding.apply {
            val context = root.context
            lifecycleOwner = lifecycleOwner
            donation = item
            goDetails.setOnClickListener {
                val intent = Intent(context, DonationDetailsActivity::class.java).apply {
                    putExtra(ID, item.id)
                }
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int = if(differ.currentList.size > 5) 5 else differ.currentList.size
}
