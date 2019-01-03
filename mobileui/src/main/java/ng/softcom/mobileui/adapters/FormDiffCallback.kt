package ng.softcom.mobileui.adapters

import androidx.recyclerview.widget.DiffUtil
import ng.softcom.models.FormElement
import ng.softcom.models.FormElementType

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

        var areContentsSame = oldFormElement.isVisible == newFormElement.isVisible

        if(newFormElement.formType == FormElementType.FORMATTED_NUMERIC){
            areContentsSame = (oldFormElement.userResponse?.stringResponse == newFormElement.userResponse?.stringResponse) && areContentsSame
        }

//        Log.e("tag", "contents are the same $areContentsSame")
        return areContentsSame

    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        // Implement method if you're going to use ItemAnimator
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}