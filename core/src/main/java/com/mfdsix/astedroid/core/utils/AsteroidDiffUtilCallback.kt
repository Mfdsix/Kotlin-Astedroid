import androidx.recyclerview.widget.DiffUtil
import com.mfdsix.astedroid.core.domain.model.Asteroid

class AsteroidDiffUtilCallback(private val oldList: List<Asteroid>, private val newList: List<Asteroid>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when (oldList[oldItemPosition].id) {
            newList[newItemPosition].id -> true
            else -> false
        }
    }

}