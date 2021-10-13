package go.kr.mapo.mapoyouth.ui.volunteer

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import go.kr.mapo.mapoyouth.databinding.ItemVolunteerRvBinding
import go.kr.mapo.mapoyouth.network.response.Volunteer
import go.kr.mapo.mapoyouth.util.ID

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-09-13
 * @desc
 */
class VolunteerListAdapter : PagingDataAdapter<Volunteer, VolunteerListAdapter.MyViewHolder>(DIFF_UTIL_VOLUNTEER) {

    override fun onBindViewHolder(holder: VolunteerListAdapter.MyViewHolder, position: Int) {
        getItem(position)?.let { item ->
            with(holder.binding) {
                val context = root.context
                volunteer = item
                root.setOnClickListener {
                    val intent = Intent(context, VolunteerDetailsActivity::class.java).apply {
                        putExtra(ID, item.id)
                    }
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VolunteerListAdapter.MyViewHolder {
        val binding = ItemVolunteerRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    inner class MyViewHolder(val binding: ItemVolunteerRvBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        val DIFF_UTIL_VOLUNTEER = object : DiffUtil.ItemCallback<Volunteer>() {
            override fun areItemsTheSame(oldItem: Volunteer, newItem: Volunteer): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: Volunteer, newItem: Volunteer): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    }
}