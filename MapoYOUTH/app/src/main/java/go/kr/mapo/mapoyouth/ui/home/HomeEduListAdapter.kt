package go.kr.mapo.mapoyouth.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import go.kr.mapo.mapoyouth.databinding.ItemHomeEduRvBinding
import go.kr.mapo.mapoyouth.network.response.Edu
import go.kr.mapo.mapoyouth.ui.edu.EduDetailsActivity
import go.kr.mapo.mapoyouth.util.ID

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-09-14
 * @desc
 */
class HomeEduListAdapter : RecyclerView.Adapter<HomeEduListAdapter.MyViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Edu>() {
        override fun areItemsTheSame(oldItem: Edu, newItem: Edu): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Edu, newItem: Edu): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<Edu>) {
        differ.submitList(list)
    }

    inner class MyViewHolder(val binding: ItemHomeEduRvBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemHomeEduRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.binding.apply {
            val context = root.context
            lifecycleOwner = lifecycleOwner
            edu = item
            goDetails.setOnClickListener {
                val intent = Intent(context, EduDetailsActivity::class.java).apply {
                    putExtra(ID, item.id)
                }
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount() = if(differ.currentList.size > 5) 5 else differ.currentList.size
}