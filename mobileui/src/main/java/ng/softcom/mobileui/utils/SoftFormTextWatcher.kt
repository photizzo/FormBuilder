package ng.softcom.mobileui.utils

import android.text.Editable
import android.text.TextWatcher
import android.util.Log


abstract class SoftFormTextWatcher : TextWatcher {
    private var isRunning = false
    private var isDeleting = false
    open var mask: String? = null

    override fun afterTextChanged(s: Editable?) {
        if (mask == null) return

        if (isRunning || isDeleting) {
            return
        }
        isRunning = true
        var sub = s.toString().getFormattedNumber(mask!!)

//        var editableLength = s.toString().length
//        if (editableLength < mask!!.length) {
//            if (mask!!.toCharArray()[editableLength] != '#') {
//                sub += mask!!.toCharArray()[editableLength]
////                s!!.append(mask!!.toCharArray()[editableLength])
//            } else if (mask!!.toCharArray()[editableLength] != '#') {
//                sub = StringBuilder(sub).insert(editableLength - 1, mask, editableLength - 1, editableLength).toString()
////                s!!.insert(editableLength - 1, mask, editableLength - 1, editableLength)
//            }
//        }
//        s!!.replace(0, editableLength, sub)
        val text = sub
        s!!.clear()
        s!!.append(text)

        isRunning = false
        Log.e("tag", "after $sub")

//        val pattern = mask!!.replace("-", ",")
//        val formatter = DecimalFormat(pattern)
//        val yourFormattedString = formatter.format(s.toString().removeDashes().toLong())
//        Log.e("tag", "after ${yourFormattedString.removeCommas()}")
//
//        updateNumberFormat(sub)


    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        isDeleting = count > after
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    abstract fun updateNumberFormat(s: CharSequence?)

    open fun applyNumberFormatting(pattern: String) {
        mask = pattern
    }


}