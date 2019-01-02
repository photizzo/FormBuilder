package ng.softcom.mobileui.adapters

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import ng.softcom.models.FormElement

class FormDiffCallback(
    private val oldItems: List<FormElement>,
    private val newItems: List<FormElement>)
    :DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].uniqueId == newItems[newItemPosition].uniqueId
    }

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldFormElement = oldItems[oldItemPosition]
        val newFormElement = newItems[newItemPosition]

        val areContentsSame = oldFormElement.isVisible == newFormElement.isVisible

        Log.e("tag", "contents are the same $areContentsSame")
        return oldItems[oldItemPosition].isVisible == newItems[newItemPosition].isVisible

    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        // Implement method if you're going to use ItemAnimator
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}