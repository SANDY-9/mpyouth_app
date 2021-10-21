package go.kr.mapo.mapoyouth.ui.youth

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import go.kr.mapo.mapoyouth.databinding.ItemYouthRvBinding
import go.kr.mapo.mapoyouth.network.response.Youth
import go.kr.mapo.mapoyouth.util.ID

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-09-09
 * @desc
 */

class YouthListAdapter : PagingDataAdapter<Youth, YouthListAdapter.MyViewHolder>(DIFF_UTIL_YOUTH) {

    override fun onBindViewHolder(holder: YouthListAdapter.MyViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.binding.apply {
                youth = item
                lifecycleOwner = lifecycleOwner
            }.also { holder.setHolderView(item) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): YouthListAdapter.MyViewHolder {
        val binding = ItemYouthRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    inner class MyViewHolder(val binding :ItemYouthRvBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setHolderView(item: Youth) = binding.apply {
            if(bindingAdapterPosition == itemCount - 1) {
                divider.visibility = View.GONE
            }
            root.setOnClickListener {
                val intent = Intent(it.context, YouthDetailsActivity::class.java).apply {
                    putExtra(ID, item.programId)
                }
                it.context.startActivity(intent)
            }
        }
    }

    companion object {
        val DIFF_UTIL_YOUTH = object : DiffUtil.ItemCallback<Youth>() {
            override fun areItemsTheSame(oldItem: Youth, newItem: Youth): Boolean {
                return oldItem.programId == newItem.programId
            }

            override fun areContentsTheSame(oldItem: Youth, newItem: Youth): Boolean {
                return oldItem == newItem
            }
        }
    }
}
