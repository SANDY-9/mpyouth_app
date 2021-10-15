package go.kr.mapo.mapoyouth.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import go.kr.mapo.mapoyouth.databinding.ItemHomeYouthRvBinding
import go.kr.mapo.mapoyouth.network.response.Youth
import go.kr.mapo.mapoyouth.ui.youth.YouthDetailsActivity
import go.kr.mapo.mapoyouth.util.ID

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-09-14
 * @desc
 */
class HomeYouthListAdapter: RecyclerView.Adapter<HomeYouthListAdapter.MyViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Youth>() {
        override fun areItemsTheSame(oldItem: Youth, newItem: Youth): Boolean {
            return oldItem.programId == newItem.programId
        }

        override fun areContentsTheSame(oldItem: Youth, newItem: Youth): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<Youth>) {
        differ.submitList(list)
    }

    inner class MyViewHolder(val binding: ItemHomeYouthRvBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = ItemHomeYouthRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.binding.apply {
            val context = root.context
            lifecycleOwner = lifecycleOwner
            youth = item
            goDetails.setOnClickListener {
                val intent = Intent(context, YouthDetailsActivity::class.java).apply {
                    putExtra(ID, item.programId)
                }
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int = if(differ.currentList.size > 5) 5 else differ.currentList.size
}