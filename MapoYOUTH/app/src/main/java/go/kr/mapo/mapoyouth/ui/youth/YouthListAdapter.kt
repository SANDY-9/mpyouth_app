package go.kr.mapo.mapoyouth.ui.youth

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.ItemYouthRvBinding
import go.kr.mapo.mapoyouth.network.repository.YouthSearchResult
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
            with(holder.binding) {
                val context = root.context
                youth = item
                lifecycleOwner = lifecycleOwner
                root.setOnClickListener {
                    val intent = Intent(context, YouthDetailsActivity::class.java).apply {
                        putExtra(ID, item.programId)
                    }
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): YouthListAdapter.MyViewHolder {
        val binding = ItemYouthRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    inner class MyViewHolder(val binding :ItemYouthRvBinding) : RecyclerView.ViewHolder(binding.root) {}

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
