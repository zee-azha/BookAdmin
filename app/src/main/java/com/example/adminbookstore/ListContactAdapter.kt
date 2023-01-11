import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.adminbookstore.MyDiffUtil
import com.example.adminbookstore.data.ResultBooks
import com.example.adminbookstore.databinding.ItemContactBinding


class ListContactAdapter :
    RecyclerView.Adapter<ListContactAdapter.ListViewHolder>() {

    private var listContact = ArrayList<ResultBooks>()

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.binding.tvTitle.text = listContact[position].title
        holder.binding.tvAuthor.text = listContact[position].author
        holder.binding.tvPrice.text = listContact[position].price.toString()
        holder.binding.tvStatus.text = listContact[position].status
        holder.itemView.setOnClickListener {
            onItemClickCallback?.onItemClicked(listContact[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listContact.size

    class ListViewHolder(val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(usersDetails: ResultBooks)
    }

    fun setData(newContactList: ArrayList<ResultBooks>) {
        val diffUtil = MyDiffUtil(listContact, newContactList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        listContact = newContactList
        diffResult.dispatchUpdatesTo(this)

    }

}