package go.kr.mapo.mapoyouth.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import go.kr.mapo.mapoyouth.databinding.ItemHomeVolunteerRvBinding
import go.kr.mapo.mapoyouth.network.response.Volunteer
import go.kr.mapo.mapoyouth.ui.volunteer.VolunteerDetailsActivity
import go.kr.mapo.mapoyouth.util.ID

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-09-14
 * @desc
 */
class HomeVolunteerListAdapter : RecyclerView.Adapter<HomeVolunteerListAdapter.MyViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Volunteer>() {
        override fun areItemsTheSame(oldItem: Volunteer, newItem: Volunteer): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Volunteer, newItem: Volunteer): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<Volunteer>) {
        differ.submitList(list)
    }
    
    inner class MyViewHolder(val binding: ItemHomeVolunteerRvBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemHomeVolunteerRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.binding.apply {
            val context = root.context
            lifecycleOwner = lifecycleOwner
            volunteer = item
            goDetails.setOnClickListener {
                val intent = Intent(context, VolunteerDetailsActivity::class.java).apply {
                    putExtra(ID, item.id)
                }
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int = if(differ.currentList.size > 5) 5 else differ.currentList.size
}
