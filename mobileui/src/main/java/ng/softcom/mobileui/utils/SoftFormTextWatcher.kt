package ng.softcom.mobileui.utils

import android.text.Editable
import android.text.TextWatcher

/**
 * custom edittext watcher
 * control formatting and updating of number format form elements
 * minimized callbacks
 */
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

        val text = sub
        s!!.clear()
        s!!.append(text)

        isRunning = false
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        isDeleting = count > after
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    open fun applyNumberFormatting(pattern: String) {
        mask = pattern
    }


}