package go.kr.mapo.mapoyouth.ui.edu

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import go.kr.mapo.mapoyouth.databinding.ItemEduRvBinding
import go.kr.mapo.mapoyouth.network.response.Edu
import go.kr.mapo.mapoyouth.util.ID

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-09-13
 * @desc
 */
class EduListAdapter : PagingDataAdapter<Edu, EduListAdapter.MyViewHolder>(DIFF_UTIL_EDU) {

    override fun onBindViewHolder(holder: EduListAdapter.MyViewHolder, position: Int) {
        getItem(position)?.let { item ->
            with(holder.binding) {
                val context = root.context
                lifecycleOwner = lifecycleOwner
                edu = item
                root.setOnClickListener {
                    val intent = Intent(context, EduDetailsActivity::class.java).apply {
                        putExtra(ID, item.id)
                    }
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EduListAdapter.MyViewHolder {
        val binding = ItemEduRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    inner class MyViewHolder(val binding: ItemEduRvBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        val DIFF_UTIL_EDU = object : DiffUtil.ItemCallback<Edu>() {
            override fun areItemsTheSame(oldItem: Edu, newItem: Edu): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Edu, newItem: Edu): Boolean {
                return oldItem == newItem
            }
        }
    }

}